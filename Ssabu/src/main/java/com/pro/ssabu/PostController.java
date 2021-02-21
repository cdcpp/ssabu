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

		// �Խñ� ��������
		String pState = "";
		if (postContent.getPstate() == 0) {
			pState = "��ΰ���";
		} else if (postContent.getPstate() == 1) {
			pState = "�ȷο����";
		} else if (postContent.getPstate() == 2) {
			pState = "�����";
		}
		m.addAttribute("pState", pState);

		// ��������
		m.addAttribute("likeCount", pService.likeCount(pIdx));
		// ���ð���
		m.addAttribute("replyCount", pService.replyCount(pIdx));

		// �������� ���
		log.debug(pIdx);
		log.debug("replyArr====" + replyArr);

		// �α����� �Ǿ����� Ȯ��
		m.addAttribute("loginUser", loginUser);

		// �Խñ�����
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
			 * String msg="����ۼ��� �����Ͽ���"; util.addMsgBack(m, msg);
			 */

		}

		MemberVO loginUser = (MemberVO) ses.getAttribute("loginUser");
		String rmidx = Integer.toString(loginUser.getMidx());

		Map<String, String> replyInfo = new HashMap<>();
		replyInfo.put("pidx", pidx);
		replyInfo.put("rmidx", rmidx);
		replyInfo.put("rcontent", rcontent);
		replyInfo.put("rstate", rstate);
		// ��۱���� ���� �߰�����

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
			msg = "���������� ���ƿ䰡 �������� �ʾҽ��ϴ�.";
			return util.addMsgLoc(m, msg, loc);
		}
		MemberVO loginUser = (MemberVO) ses.getAttribute("loginUser");
		String userMidx = Integer.toString(loginUser.getMidx());
		if (pMidx.equals(userMidx)) {
			loc = "showpost?pIdx=" + pidx;
			msg = "���αۿ��� ���ƿ並 ������ �� �����ϴ�.";
			return util.addMsgLoc(m, msg, loc);
		}

		Map<String, String> likeUserMap = new HashMap<>();
		likeUserMap.put("pidx", pidx);
		likeUserMap.put("midx", userMidx);

		// �̹� ��������� ���� �Խñۿ� ���ƿ並 �������� üũ
		Integer likeChk = pService.alreadyLikeChk(likeUserMap);

		log.info("likeChk=========" + likeChk);
		if (likeChk == null) {
			// �̹� ���ƿ並 �����Խñ���
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

	/** �۾��� ��ư Ŭ���� �۾��� ȭ�� ��ȯ �� �ӽ������ �̹������� ����ó�� */
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
			log.info("�ݺ��� ����");
			log.info("�� ������ ==" + arr.get(i));
			File delFile = new File(upDir, arr.get(i));
			boolean b = delFile.delete();
		}

		log.info("�ݺ��� ����");
		int n = pService.deleteImages();
		m.addAttribute("bidx", bidx);
		return "post/createPost";
	}

	/** �۾��� ȭ�鿡�� ���� ���ε� ó�� */
	@PostMapping("/imageUpload")
	public String imageUpload(Model m, HttpServletRequest req, @RequestParam(defaultValue = "") MultipartFile file) {
		log.info("userfile=" + file.getOriginalFilename());

		String upDir = req.getServletContext().getRealPath("/post_images");
		log.info("upDir===" + upDir);
		// [2] ���� ���ε� ó��
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
						log.error("��ǰ������� ���ε� �� ����: " + e.getMessage());
					}
				} // if
			} // for
		} // if
		return "";
	}

	/** �۾��� ��ư Ŭ���� �۾��� ó��(�±����� �� �ӽ������ �̹����� �۹�ȣ �ο�) */
	@PostMapping("/postInsert")
	public String InsertPost(Model m, @ModelAttribute("post") PostVO post, HttpSession ses,
			@RequestParam("tagArr") String tname, @RequestParam(defaultValue = "0") String bidx) {
		// ���� �� �ۼ��ڰ� ���� ����� �Խ��� ��ȣ ��������
		if (bidx.equals("0")) {
			bidx = (String) ses.getAttribute("bidx");
		}

		// ���� �� �ۼ��� ȸ����ȣ ��������
		MemberVO mvo = (MemberVO) ses.getAttribute("loginUser");
		post.setMIdx(mvo.getMidx());
		log.info("bidx====??+===" + bidx);
		post.setBIdx(Integer.parseInt(bidx));
		int n = pService.insertPost(post);
		int pidx = pService.selectPidx();
		log.info("pidx====!!!!==" + pidx);
		log.info(post);
		log.info("���ۼ�n====" + n);
		int imgCount = pService.selectImgCount();// �̹����� �ø��� �ʰ� �۸� �÷��� ��츦 �˱�����
		log.info("imgCount==" + imgCount);
		if (imgCount == 0) {
			ImageFileVO imageVO = new ImageFileVO();
			imageVO.setImgName("noImage");
			imageVO.setImgName_Origin("noImage");
			int n2 = pService.uploadImage(imageVO);// noImage��� �̸����� DB�� ���� �־��ش�.
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

	/** �ӽ������ �̹��� ������ ���ε����� �� DB���� ����ó�� */
	@GetMapping("/removeUpFile")
	public String removeUpFile(Model m, @RequestParam("filename") String filename, HttpServletRequest req) {
		log.info("filename====!!+!+!+!+!+=" + filename);
		String imgName = pService.selectImgName(filename);
		log.info("imgName=======!!!!" + imgName);

		String upDir = req.getServletContext().getRealPath("/post_images");

		File delFile = new File(upDir, imgName);
		boolean b = delFile.delete();
		log.info("������==" + b);

		int n = pService.deleteImage(imgName);
		// log.info("�ͽ�===="+filename);
		return "";
	}

	// ���� ����� ����(�ߺ�x)
	public String UploadFile(String originName) {
		UUID uuid = UUID.randomUUID();
		String saveName = uuid.toString() + "_" + originName;
		return saveName;

	}

}
