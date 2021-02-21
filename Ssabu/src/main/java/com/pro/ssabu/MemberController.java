package com.pro.ssabu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.domain.BoardVO;
import com.board.service.BoardService;
import com.member.domain.MemberVO;
import com.member.domain.NotUserException;
import com.member.service.MemberService;
import com.post.service.PostService;
import com.pro.util.CommonUtil;
import com.pro.util.DuplicateLoginPrevent;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemberController extends DuplicateLoginPrevent{

	@Inject
	private MemberService memberService;

	@Inject
	private BoardService boardService;

	@Inject
	private PostService postService;

	@Inject
	private CommonUtil util;
	
	@Inject
	PostController pCon;

	/** 회원가입 처리 */
	@GetMapping("/join")
	public String joinPageView(Model m) {
		return "join/join1";
	}

	/* 회원가입 페이지 이동 */
	@GetMapping("/memberJoin")
	public String memberJoin() {
		return "join/join2";
	}

	/* 이메일(아이디) 중복확인 버튼 클릭시 메소드 호출 */
	@GetMapping("/emailCheck")
	public @ResponseBody Map<String, Integer> emailCheck(Model m, @RequestParam(defaultValue = "") String email) {
		Map<String, Integer> map = new HashMap<>();
		int n = memberService.emailCheck(email);
		map.put("check", n);
		return map;
	}

	@GetMapping("/hpCheck")
	public @ResponseBody Map<String, Integer> hpCheck(Model m, @RequestParam(defaultValue = "") String hp) {
		Map<String, Integer> map = new HashMap<>();
		int n = memberService.hpCheck(hp);
		map.put("hpcheck", n);
		return map;
	}

	/* 회원가입 성공시 메인화면을 호출하는 메소드 */
	@PostMapping("/memberJoin")
	public String joinInsert(Model m, @ModelAttribute("member") MemberVO member) {
		log.info("member=" + member);
		member.setPInfo("안녕하세요~" + member.getName() + "의 홈페이지입니다");
		member.setMImage("noProfile.jpg");
		int n = memberService.insertMember(member);
		MemberVO mem = memberService.selectMember(member.getEmail());

		BoardVO b = new BoardVO();
		b.setMidx(mem.getMidx());
		b.setBname("사진첩");
		b.setBstate(0); // bstate 0 : 전체공개 || 1: 팔로잉(팔로우)공개 || 2: 나만보기(비공개) 기본값: 0
		int c = boardService.insertBoard(b);

		return "main";
	}

	@PostMapping("/subLoginInfo")
	public String mainLogin(Model m, HttpSession ses, @RequestParam(defaultValue = "") String email,
			@RequestParam(defaultValue = "") String pwd) throws NotUserException {
		log.info("id====" + email);
		log.info("pwd====" + pwd);
		// 1. 아이디와 비번창의 공백확인
		if (email == null || email.trim().isEmpty() || pwd == null || pwd.trim().isEmpty()) {
			return "redirect:main";
		}
		MemberVO loginUser = memberService.userLogin(email, pwd);

		String bidx = boardService.selectNormalBoard(loginUser.getMidx());
		log.info("bidx====" + bidx);
		// List<PostVO> post = postService.selectAllPost(loginUser.getMidx());

		// m.addAttribute("post", post);
		boolean result=false;
		String msg="",loc="";
		if (loginUser != null) {
			ses.setAttribute("dupl", this);
			if(super.isUsing(loginUser.getEmail(), ses)){                // 기존 로그인 된 회원이면
                result=super.removeLoginInfo(ses, loginUser.getEmail());        // 기존 세션 정보 삭제
            }
			if(result) {
				msg="이미 로그인된 아이디 입니다.";
				loc="main";
				return util.addMsgLoc(m, msg, loc);
			}
			super.setSession(ses, loginUser.getEmail());
			msg=loginUser.getName()+"님 환영합니다.";
			ses.setAttribute("loginUser", loginUser);
			ses.setAttribute("bidx", bidx);
		}

		return "redirect:mainboard";
	}

	@ExceptionHandler(NotUserException.class)
	public String exceptionHandle(Exception ex) {
		log.error(ex.getMessage());
		// 예외를 처리해줄 뷰 네임 반환

		return "errorAlert";
		// WEB-INF/views/login/errorAlert.jsp
		// 위 페이지에서는 page지시어에 isErrorPage="true"값을 지정하여
		// exception내장객체를 사용할 수 있도록 하자
	}

	/* 아이디 비밀번호찾기 */
	@GetMapping("/findUser")
	public String findUserForm() {
		return "/member/findUser";
	}

	/* 아이디 찾기 메소드 */
	@PostMapping("/searchId")
	public @ResponseBody Map<String, String> userIdSearch(@RequestParam("name") String name,
			@RequestParam("hp") String hp) {
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("hp", hp);

		String result = memberService.searchId(map);
		map.put("email", result);
		return map;
	}

	/* 비밀번호 찾기 메소드 */
	@PostMapping("/searchPwd")
	public @ResponseBody Map<String, String> userpwdSearch(@RequestParam("email") String email,
			@RequestParam("hp") String hp, @RequestParam("name") String name) {
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("hp", hp);
		map.put("email", email);

		String pwdChange = memberService.searchPwd(map);
		map.put("pwdChange", pwdChange);

		return map;
	}

	/* 비밀번호 변경 메소드 */
	@PostMapping("/changePwd")
	public @ResponseBody Map<String, Integer> userPwdUpdate(@RequestParam("changePwd") String changePwd,
			@RequestParam("conPwd") String conPwd, @RequestParam("email") String email) {
		Map<String, String> map = new HashMap<>();
		map.put("changePwd", changePwd);
		map.put("conPwd", conPwd);
		map.put("email", email);

		int result = memberService.updatePwd(map);
		Map<String, Integer> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}

	@GetMapping("/logout")
	public String logout(HttpSession ses) {
		// 세션 무효화
	
		ses.invalidate();
		return "redirect:main";
	}

	// 팔로잉
	@RequestMapping("/doFollow")
	public @ResponseBody int doFollow(@RequestParam(defaultValue="0")String mIdx,HttpSession ses) {
				
		MemberVO mvo=(MemberVO)ses.getAttribute("loginUser");
		int count = memberService.countFollow(mvo.getMidx());
		log.info("count==="+count);
		Map<String,String> map=new HashMap<>();
		int n=0;
		int loginMidx=mvo.getMidx();
		map.put("loginMidx", String.valueOf(loginMidx));
		
		
		
		if(count==0) {
			map.put("mIdx", mIdx);
			n=memberService.insertFollow(map);
		}else {
			String follower = memberService.selectFollow(loginMidx);
			map.put("mIdx",follower+","+mIdx);
			n=memberService.updateFollow(map);
		}
		
		
		return n;
	}////////

	// 언팔
	@RequestMapping("/unFollow")
	public @ResponseBody String unFollow(@RequestParam(defaultValue="0")String mIdx,HttpSession ses,Model m) {
		
		MemberVO mvo=(MemberVO)ses.getAttribute("loginUser");
		int loginMidx=mvo.getMidx();
		
		String follow=memberService.selectFollow(loginMidx);
		String updateFollow="";
		log.info("follow=="+follow);
		
		String[] followArr = follow.split(",");
		
		
		for(int i=0;i<followArr.length;i++) {
		 log.info("followArr====="+followArr[i].trim());
			if(!mIdx.equals(followArr[i]))  {
				if(updateFollow.equals("")) {
					log.info(followArr[i].trim());
					updateFollow+=followArr[i].trim();
				}else {
					updateFollow+=","+followArr[i].trim();
				}
			}
			
		}
		
		log.info("upFol===="+updateFollow);
		
		Map<String,String> map=new HashMap<>();
		int n=0;
		map.put("loginMidx", String.valueOf(loginMidx));
		
		
			String follower = memberService.selectFollow(loginMidx);
			map.put("mIdx",updateFollow);
			n=memberService.updateFollow(map);
			follower=memberService.selectFollow(loginMidx);
			if(follower==null) {
				int deleteF=mvo.getMidx();
				memberService.deleteFollow(deleteF);
			}
		String state = memberService.compareFollow(Integer.parseInt(mIdx), mvo.getMidx());
		
		return state;
	}
	//팔로잉목록
	@RequestMapping("/showfingList")
	public @ResponseBody List<MemberVO> showfingList(@RequestParam(defaultValue="0")String mIdx,Model m,HttpSession ses) {
		
		MemberVO mvo=(MemberVO)ses.getAttribute("loginUser");
		int loginMidx=mvo.getMidx();
		
		List<MemberVO> fing = new ArrayList<>();
		
		String follow=memberService.selectFollow(loginMidx);
		String[] followArr = follow.split(",");
		
		for(int i=0;i<followArr.length;i++) {
			MemberVO fvo=memberService.findUserByMidx(followArr[i]);
			fing.add(fvo);
		}
		
		
		return fing;
	}
	//팔로워목록
	@RequestMapping("/showfwerList")
	public @ResponseBody List<MemberVO> showfwerList(@RequestParam(defaultValue="0")String mIdx,Model m,HttpSession ses){
		
		MemberVO mvo=(MemberVO)ses.getAttribute("loginUser");
		int loginMidx=mvo.getMidx();
		List<MemberVO> fwer = new ArrayList<>();
		List<Integer> list = memberService.selectFwer(loginMidx);
		for(int i=0;i<list.size();i++) {
			MemberVO fvo=memberService.findUserByMidx(String.valueOf(list.get(i)));
			fwer.add(fvo);
		}
		
		return fwer;
	}
	
	
	/*회원수정 메소드*/
	@GetMapping("/userEdit")
	public String userEdit() {
		return "/member/userEdit";
	}
	
	@RequestMapping("/memberEdit")
	public String memberEdit(@ModelAttribute("member") MemberVO member ,HttpServletRequest req
			,HttpSession ses) {
		String upDir = req.getServletContext().getRealPath("/profile_images");
		log.info("upDir===" + upDir);
		// [2] 파일 업로드 처리

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;

		List<MultipartFile> mList = mr.getFiles("multiImage");
		if (mList != null) {
			for (int i = 0; i < mList.size(); i++) {
				MultipartFile mf = mList.get(i);
				if (!mf.isEmpty()) {
					try {
						String imgName = pCon.UploadFile(mf.getOriginalFilename());
						log.info(imgName);
						mf.transferTo(new File(upDir, imgName));
						member.setMImage(imgName);
						
					} catch (IOException e) {
						log.error("상품등록파일 업로드 중 에러: " + e.getMessage());
					}
				} // if
			} // for
		} // if
		memberService.editMember(member);
		MemberVO mmvo=(MemberVO)ses.getAttribute("loginUser");
		mmvo=memberService.selectMember(mmvo.getEmail());
		ses.setAttribute("loginUser", mmvo);
		log.info("member= "+member);
		return "redirect:mainboard";
	}
	
	/*회원 탈퇴 메소드*/
	@RequestMapping(method = RequestMethod.GET, value="/updatecheckpwd")
	public String updatecheckpwd(@RequestParam(defaultValue="0") int midx) {
		log.info("bpwd=="+midx);
		List<Integer> list = memberService.selectFwer(midx);
		if(list!=null) {
			for(int j=0;j<list.size();j++) {
				String follow=memberService.selectFollow(list.get(j));
				
				String updateFollow="";
				log.info("follow=="+follow);
				
				String[] followArr = follow.split(",");
				
				for(int i=0;i<followArr.length;i++) {
				 log.info("followArr====="+followArr[i].trim());
					if(midx!=(Integer.parseInt(followArr[i])))  {
						if(updateFollow.equals("")) {
							log.info(followArr[i].trim());
							updateFollow+=followArr[i].trim();
						}else {
							updateFollow+=","+followArr[i].trim();
						}
					}
					
				}
				
				log.info("upFol===="+updateFollow);
				
				Map<String,String> map=new HashMap<>();
				map.put("loginMidx", String.valueOf(list.get(j)));
				String follower = memberService.selectFollow(list.get(j));
				map.put("mIdx",updateFollow);
				memberService.updateFollow(map);
			}
		}
		int result = memberService.dropMember(midx);
		return "redirect:main";
	}
	

}/////////////////////////////////////////////////
