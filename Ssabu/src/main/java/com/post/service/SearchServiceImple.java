package com.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.domain.MemberVO;
import com.post.domain.PostVO;
import com.post.domain.TagVO;
import com.post.mapper.SearchMapper;

@Service
public class SearchServiceImple implements SearchService {

	@Autowired
	SearchMapper sMapper;
	
	@Override
	public List<MemberVO> searchMemberList(String word) {
		return sMapper.searchMemberList(word);
	}

	@Override
	public List<TagVO> searchTagForPidx(String word) {
		return sMapper.searchTagForPidx(word);
	}

	@Override
	public PostVO searchPost(int pidx) {
		return sMapper.searchPost(pidx);
	}

	@Override
	public PostVO newPost(String mIdx) {
		return sMapper.newPost(mIdx);
	}

}
