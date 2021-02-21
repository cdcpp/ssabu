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
		
		//sWord�� �̸� Ȥ�� �±׸� �޴°��̴�. (�̸��� �ƴ�)
		
		//�ι��˻�---------------------------------------------------------------------------------------------------
		
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
					sex="��";
				}else if(sex.equals("f")){
					sex="��";
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
		
		
		//�±װ˻�---------------------------------------------------------------------------------------------------
		//�±����̺��� �ش� �±װ� �����ϴ��� �˻��Ѵ�.
		List<TagVO> searchedTagList=sService.searchTagForPidx(word);
		
		if(searchedTagList!=null) {
			//log.info("searchTagForPidx====="+searchedTagList);
			
			//�ݺ����� ���鼭 �ش� �±װ� �ִ� �Խù���ȣ�� �����´�.
			List<PostVO> searchedPostList=new ArrayList<>();
			Iterator<TagVO> itr=searchedTagList.iterator();
			while(itr.hasNext()) {
				int pidx=itr.next().getPIdx();
				//log.info("pidx====="+pidx);
				PostVO pvo=sService.searchPost(pidx);
				searchedPostList.add(pvo);
			}//while��
			m.addAttribute("pList",searchedPostList);
			int pListCount=searchedPostList.size();
			m.addAttribute("pListCount",pListCount);
			
			//������ �Խù���ȣ�� �Խñ��� select�Ѵ�.
		}//if��
		return "/search/search";
	}
}
