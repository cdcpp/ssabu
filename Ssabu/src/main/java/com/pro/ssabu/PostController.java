package com.pro.ssabu;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.member.domain.MemberVO;
import com.post.domain.ImageFileVO;
import com.post.domain.LikeVO;
import com.post.domain.PostVO;
import com.post.domain.ReplyVO;
import com.post.domain.TagVO;
import com.post.service.PostService;
import com.pro.util.CommonUtil;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class PostController {

	@Autowired
	private PostService pService;
	@Autowired
	private CommonUtil util;

	@RequestMapping("/showpost")
	public String showPost(Model m, HttpSession ses, @RequestParam(defaultValue = "37") int pIdx) {
		/* List<PostVO> pInfo=pService.getPost(pIdx); */
		MemberVO pEditer = pService.getPostEditer(pIdx);
		PostVO postContent = pService.getPost(pIdx);
		List<LikeVO> postLike = pService.getLike(pIdx);
		List<ReplyVO> replyArr = pService.getReply(pIdx);
		List<TagVO> postTag = pService.getTag(pIdx);
		List<ImageFileVO> postImageArr = pService.getImageFile(pIdx);

		log.info("pEditer====" + pEditer);
		if (postContent == null || pEditer == null) {
			return "board/home";
		}
		MemberVO loginUser = (MemberVO) ses.getAttribute("loginUser");

		// 게시글 공개범위
		String pState = "";
		if (postContent.getPstate() == 0) {
			pState = "모두공개";
		} else if (postContent.getPstate() == 1) {
			pState = "팔로우공개";
		} else if (postContent.getPstate() == 2) {
			pState = "비공개";
		}
		m.addAttribute("pState", pState);

		// 공감갯수
		m.addAttribute("likeCount", pService.likeCount(pIdx));
		// 리플갯수
		m.addAttribute("replyCount", pService.replyCount(pIdx));

		// 리플정보 담기
		log.debug(pIdx);
		log.debug("replyArr====" + replyArr);

		// 로그인이 되었는지 확인
		m.addAttribute("loginUser", loginUser);

		// 게시글정보
		m.addAttribute("pe", pEditer);
		m.addAttribute("pidx", pIdx);
		m.addAttribute("postContent", postContent);
		m.addAttribute("postLike", postLike);
		m.addAttribute("postReply", replyArr);
		m.addAttribute("postTag", postTag);
		m.addAttribute("postImage", postImageArr);

		return "board/article";
	}

	@RequestMapping("/replySub")
	public String subReply(Model m, HttpSession ses,
			@RequestParam(defaultValue = "", name = "replyContentF") String rcontent,
			@RequestParam(defaultValue = "", name = "secretReplyF") String rstate,
			@RequestParam(defaultValue = "", name = "pidxForReply") String pidx) {
		if (rcontent == null || rstate == null || pidx == null || rcontent.trim().isEmpty() || rstate.trim().isEmpty()
				|| pidx.trim().isEmpty()) {
			/*
			 * String msg="댓글작성에 실패하였음"; util.addMsgBack(m, msg);
			 */

		}

		MemberVO loginUser = (MemberVO) ses.getAttribute("loginUser");
		String rmidx = Integer.toString(loginUser.getMidx());

		Map<String, String> replyInfo = new HashMap<>();
		replyInfo.put("pidx", pidx);
		replyInfo.put("rmidx", rmidx);
		replyInfo.put("rcontent", rcontent);
		replyInfo.put("rstate", rstate);
		// 답글기능은 추후 추가예정

		int result = pService.subReply(replyInfo);
		log.info("result===========" + result);
		String loc = "showpost?pIdx=" + pidx + "#replyContent";
		return util.addLoc(m, loc);
	}

	@RequestMapping("/likeUp")
	public String likeUp(Model m, HttpSession ses, @RequestParam(defaultValue = "", name = "likePidxF") String pidx,
			@RequestParam(defaultValue = "", name = "likeMyPost") String pMidx) {
		String loc = "";
		String msg = "";
		if (pidx == null || pMidx == null || pidx.trim().isEmpty() || pMidx.trim().isEmpty()) {
			loc = "showpost?pidx=" + pidx;
			msg = "오류로인해 좋아요가 눌러지지 않았습니다.";
			return util.addMsgLoc(m, msg, loc);
		}
		MemberVO loginUser = (MemberVO) ses.getAttribute("loginUser");
		String userMidx = Integer.toString(loginUser.getMidx());
		if (pMidx.equals(userMidx)) {
			loc = "showpost?pIdx=" + pidx;
			msg = "본인글에는 좋아요를 누르실 수 없습니다.";
			return util.addMsgLoc(m, msg, loc);
		}

		Map<String, String> likeUserMap = new HashMap<>();
		likeUserMap.put("pidx", pidx);
		likeUserMap.put("midx", userMidx);

		// 이미 같은사람이 같은 게시글에 좋아요를 눌렀는지 체크
		Integer likeChk = pService.alreadyLikeChk(likeUserMap);

		log.info("likeChk=========" + likeChk);
		if (likeChk == null) {
			// 이미 좋아요를 누른게시글임
			int likeResult = pService.likeUp(likeUserMap);
			log.info("likere=="+likeResult);
			int likeCount=pService.uplikeCount(likeUserMap);
		} else {
			int unLikeResult = pService.likeDown(likeUserMap);
			int likeCount=pService.downlikeCount(likeUserMap);
		}

		loc = "showpost?pIdx=" + pidx;
		return util.addLoc(m, loc);
	}

	/** 글쓰기 버튼 클릭시 글쓰기 화면 전환 및 임시저장된 이미지파일 삭제처리 */
	@GetMapping("/createPost")
	public String createPostView(Model m, HttpSession ses, HttpServletRequest req,
			@RequestParam(defaultValue = "0") String bidx) {

		if (bidx.equals("0")) {
			bidx = (String) ses.getAttribute("bidx");
		}

		List<String> arr = pService.findPidxZero();
		log.info("arr=========" + arr.toString());
		String upDir = req.getServletContext().getRealPath("/post_images");
		for (int i = 0; i < arr.size(); i++) {
			log.info("반복문 입장");
			log.info("글 제목은 ==" + arr.get(i));
			File delFile = new File(upDir, arr.get(i));
			boolean b = delFile.delete();
		}

		log.info("반복문 퇴장");
		int n = pService.deleteImages();
		m.addAttribute("bidx", bidx);
		return "post/createPost";
	}

	/** 글쓰기 화면에서 파일 업로드 처리 */
	@PostMapping("/imageUpload")
	public String imageUpload(Model m, HttpServletRequest req, @RequestParam(defaultValue = "") MultipartFile file) {
		log.info("userfile=" + file.getOriginalFilename());

		String upDir = req.getServletContext().getRealPath("/post_images");
		log.info("upDir===" + upDir);
		// [2] 파일 업로드 처리
		ImageFileVO imageVO = new ImageFileVO();

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;

		List<MultipartFile> mList = mr.getFiles("file");
		if (mList != null) {
			for (int i = 0; i < mList.size(); i++) {
				MultipartFile mf = mList.get(i);
				if (!mf.isEmpty()) {
					try {
						String imgName = UploadFile(mf.getOriginalFilename());
						m.addAttribute("filename", imgName);
						log.info(imgName);
						mf.transferTo(new File(upDir, imgName));
						imageVO.setImgName(imgName);
						imageVO.setImgName_Origin(mf.getOriginalFilename());
						int n = pService.uploadImage(imageVO);
						log.info("prod>>>>" + imageVO);
						log.info("n====" + n);
					} catch (IOException e) {
						log.error("상품등록파일 업로드 중 에러: " + e.getMessage());
					}
				} // if
			} // for
		} // if
		return "";
	}

	/** 글쓰기 버튼 클릭시 글쓰기 처리(태그저장 및 임시저장된 이미지에 글번호 부여) */
	@PostMapping("/postInsert")
	public String InsertPost(Model m, @ModelAttribute("post") PostVO post, HttpSession ses,
			@RequestParam("tagArr") String tname, @RequestParam(defaultValue = "0") String bidx) {
		// 현재 글 작성자가 글을 등록할 게시판 번호 가져오기
		if (bidx.equals("0")) {
			bidx = (String) ses.getAttribute("bidx");
		}

		// 현재 글 작성자 회원번호 가져오기
		MemberVO mvo = (MemberVO) ses.getAttribute("loginUser");
		post.setMIdx(mvo.getMidx());
		log.info("bidx====??+===" + bidx);
		post.setBIdx(Integer.parseInt(bidx));
		int n = pService.insertPost(post);
		int pidx = pService.selectPidx();
		log.info("pidx====!!!!==" + pidx);
		log.info(post);
		log.info("글작성n====" + n);
		int imgCount = pService.selectImgCount();// 이미지를 올리지 않고 글만 올렸을 경우를 알기위해
		log.info("imgCount==" + imgCount);
		if (imgCount == 0) {
			ImageFileVO imageVO = new ImageFileVO();
			imageVO.setImgName("noImage");
			imageVO.setImgName_Origin("noImage");
			int n2 = pService.uploadImage(imageVO);// noImage라는 이름으로 DB에 값을 넣어준다.
			log.info("n2===" + n2);
		}
		pService.updateImgPidx(pidx);

		// log.info("tagArr==="+tname.toString());
		TagVO tag = new TagVO();
		tag.setPIdx(pidx);
		tag.setTName(tname);

		pService.insertTag(tag);
		ses.setAttribute("bidx", bidx);
		return "redirect:mainboard";
	}

	/** 임시저장된 이미지 삭제시 업로드폴더 및 DB에서 삭제처리 */
	@GetMapping("/removeUpFile")
	public String removeUpFile(Model m, @RequestParam("filename") String filename, HttpServletRequest req) {
		log.info("filename====!!+!+!+!+!+=" + filename);
		String imgName = pService.selectImgName(filename);
		log.info("imgName=======!!!!" + imgName);

		String upDir = req.getServletContext().getRealPath("/post_images");

		File delFile = new File(upDir, imgName);
		boolean b = delFile.delete();
		log.info("삭제는==" + b);

		int n = pService.deleteImage(imgName);
		// log.info("익스===="+filename);
		return "";
	}

	// 파일 덮어쓰기 방지(중복x)
	public String UploadFile(String originName) {
		UUID uuid = UUID.randomUUID();
		String saveName = uuid.toString() + "_" + originName;
		return saveName;

	}

}
