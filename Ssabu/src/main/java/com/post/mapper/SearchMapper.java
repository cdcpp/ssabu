package com.post.mapper;

import java.util.List;

import com.member.domain.MemberVO;
import com.post.domain.PostVO;
import com.post.domain.TagVO;

public interface SearchMapper {
	//해당 이름을 가진 멤버의 리스트를 가져온다.
		List<MemberVO> searchMemberList(String word);

		//검색어를 통해 태그테이블을 훑어 해당 태그가 있는 태그리스트를 가져온다.
		List<TagVO> searchTagForPidx(String word);

		//찾은 태그를 갖고있는 게시글 번호 가져오기
		PostVO searchPost(int pidx);
		
		//인물검색에서 해당인물이 가장 최근에 쓴 글을 가져오기
		PostVO newPost(String mIdx);
}
