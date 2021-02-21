package com.pro.ssabu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.BoardVO;
import com.board.service.BoardService;
import com.member.domain.MemberVO;
import com.member.service.MemberService;
import com.post.domain.PostVO;
import com.post.service.PostService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class BoardController {

	@Inject
	private PostService postService;

	@Inject
	private BoardService boardService;
	
	@Inject
	private MemberService memberService;

	@RequestMapping("/createBoard")
	public String createBoard(Model m, HttpSession ses, @RequestParam("bname") String bname,
			@RequestParam("bstate") int bstate) {
		// log.info("bname=="+bname+", bstate===="+bstate);
		// private int midx;
		BoardVO board = new BoardVO();
		MemberVO loginUser = (MemberVO) ses.getAttribute("loginUser");
		board.setMidx(loginUser.getMidx());
		board.setBname(bname);
		board.setBstate(bstate);

		boardService.insertBoard(board);
		return "redirect:mainboard";
	}
	
	
	
	
	// ¢¬¨­AIE¡©¢¬e(¢¬¨­AI¡ÆO¨öA¨¡C) ¨¬¢¬¢¯¨ÏAO¡¾a
	@RequestMapping("/mainboard")
	public String mainBoardView(Model m, HttpSession ses, @RequestParam(defaultValue = "0") String bidx,
			@RequestParam(defaultValue = "date") String sort,@RequestParam(defaultValue="") String tname,
			@RequestParam(defaultValue = "0")String mIdx,
			@RequestParam(defaultValue = "0")int flag) {
		
		log.info("sort====" + sort);
		log.info("tagname==="+tname);
		
		if (bidx.equals("0")) {
			bidx = (String) ses.getAttribute("bidx");
		}
		
		log.info(bidx);
		// log.info("loginUser===" + loginUser.toString());

		List<String> ym = postService.getYearMonth();
		// log.info(ym);
		Map<String, String> map = new HashMap<>();
		Map<String, List> p = new HashMap<>();
		MemberVO mvo = (MemberVO)ses.getAttribute("loginUser");
		log.info("mvo.getMidx==="+mvo.getMidx());
	
		
		if(flag!=0) {
			
			mIdx = boardService.randomMidx();
			bidx = boardService.selectNormalBoard(Integer.parseInt(mIdx));
			flag=0;
			
		}
		
		if(mIdx.equals("0")) {
			mIdx = String.valueOf(mvo.getMidx());
		}else {
			bidx = boardService.selectNormalBoard(Integer.parseInt(mIdx));
		}
		
	
		String state = memberService.compareFollow(Integer.parseInt(mIdx), mvo.getMidx());
		log.info("MIDX======"+mIdx);
		log.info("mvoMIDX---"+mvo.getMidx());
		log.info("state----"+state);
		m.addAttribute("state",state);
		map.put("state", state);
		
		log.info("midx==="+mIdx);
		for (int i = 0; i < ym.size(); i++) {
			map.put("midx", mIdx);
			log.info("ym===" + ym.get(i));
			map.put("ym", ym.get(i));
			map.put("bidx", bidx);
			map.put("sort", sort);
			map.put("tagname", tname);
			List<PostVO> post = postService.selectAllPost(map);
			if (post == null || post.isEmpty()) {
			} else {
				p.put(ym.get(i), post);
			}
			log.info("Post=" + post);
		}
		Map<String, String> tnameMap = new HashMap<>();

		List<Integer> pArr = postService.selectPidxBymidx(Integer.parseInt(mIdx));
		if (pArr != null) {
			for (int i = 0; i < pArr.size(); i++) {
				int pidx = pArr.get(i);
				List<String> tArr = postService.selectTname(pidx);
				if (tArr != null) {
					for (int j = 0; j < tArr.size(); j++) {
						String tname1 = tArr.get(j);

						if (tname1 != null) {
							String[] tnameArr = tname1.split(",");
							for (int k = 0; k < tnameArr.length; k++) {
								tnameMap.put(tnameArr[k], tnameArr[k]);
							}
						} else {
							continue;
						}
					}
				} else {
					continue;
				}
			}

		}
		
		
		List<BoardVO> board = boardService.getUserBoard(Integer.parseInt(mIdx));
		
		
		MemberVO mInfo = memberService.findUserByMidx(mIdx);
		int pCount=memberService.selectPostCount(Integer.parseInt(mIdx));
		List<Integer> fwer=memberService.selectFwer(Integer.parseInt(mIdx));
		log.info("minfo===="+mInfo.toString());
		/*AA¡¾¡¿ ¡ÆE¡ío EA A¢´¡¤AA¨¬??*/
		m.addAttribute("mInfo",mInfo);
		m.addAttribute("tname1", tnameMap);
		m.addAttribute("boards", board);
		m.addAttribute("post", p);
		m.addAttribute("bidx", bidx);
		m.addAttribute("pCount",pCount);
		m.addAttribute("fwer",fwer.size());
		return "board/mainboard";

	}
	


}
