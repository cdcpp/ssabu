package com.pro.ssabu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.member.domain.MemberVO;
import com.post.domain.PostVO;
import com.post.domain.TagVO;
import com.post.service.SearchService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class SearchController {
	
	@Autowired
	SearchService sService;
	
	@RequestMapping("/searchF")
	public String showSearch(Model m,@RequestParam(defaultValue="",name="searchText") String sWord) {
		
		//sWord는 이름 혹은 태그를 받는것이다. (이메일 아님)
		
		//인물검색---------------------------------------------------------------------------------------------------
		
		String word=sWord.trim();
		//log.info("word====="+word);
		List<MemberVO> memberList=sService.searchMemberList(word);
		//log.info("memberList====="+memberList);
		if(memberList!=null) {
			Iterator<MemberVO> miter=memberList.iterator();
			
			List<Map<String,String>> mlist=new ArrayList<>();
			MemberVO mvo=new MemberVO();
			while(miter.hasNext()){
				mvo=miter.next();
				Map<String, String> memberListMap=new HashMap<>();	
				
				String mIdx=Integer.toString(mvo.getMidx());
				String mImage=mvo.getMImage();
				if(mImage==null) {
					mImage="noprofile.jpg";
				}
				
				String name=mvo.getName();
				String email=mvo.getEmail();
				String sex=mvo.getSex();
				if(sex.equals("m")) {
					sex="남";
				}else if(sex.equals("f")){
					sex="여";
				}else {
					sex="";
				}
				
				memberListMap.put("mIdx", mIdx);
				memberListMap.put("mImage", mImage);
				memberListMap.put("name", name);
				memberListMap.put("email", email);
				memberListMap.put("sex", sex);
				
				PostVO newPost=sService.newPost(mIdx);
				//log.info("newPost===="+newPost);
				
				if(newPost!=null) {
					String newPostSubject=newPost.getSubject();
					String pIdx=Integer.toString(newPost.getPIdx());
					//log.info("pIdx======="+pIdx);
					memberListMap.put("newPostSubject", newPostSubject);
					memberListMap.put("pIdx", pIdx);
				}else {
					
				}
				
				mlist.add(memberListMap);
				log.info("memberListMap===="+memberListMap);
				
			}
			m.addAttribute("mList",mlist);
			
			
			log.info("mlist===="+mlist);
			int mListCount=mlist.size();
			m.addAttribute("mListCount",mListCount);
			
		}else {
			
		}
		
		
		//태그검색---------------------------------------------------------------------------------------------------
		//태그테이블에서 해당 태그가 존재하는지 검색한다.
		List<TagVO> searchedTagList=sService.searchTagForPidx(word);
		
		if(searchedTagList!=null) {
			//log.info("searchTagForPidx====="+searchedTagList);
			
			//반복문을 돌면서 해당 태그가 있는 게시물번호를 가져온다.
			List<PostVO> searchedPostList=new ArrayList<>();
			Iterator<TagVO> itr=searchedTagList.iterator();
			while(itr.hasNext()) {
				int pidx=itr.next().getPIdx();
				//log.info("pidx====="+pidx);
				PostVO pvo=sService.searchPost(pidx);
				searchedPostList.add(pvo);
			}//while문
			m.addAttribute("pList",searchedPostList);
			int pListCount=searchedPostList.size();
			m.addAttribute("pListCount",pListCount);
			
			//가져온 게시물번호로 게시글을 select한다.
		}//if문
		return "/search/search";
	}
}
