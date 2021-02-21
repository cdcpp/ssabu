package com.board.mapper;

import java.util.List;

import com.board.domain.BoardVO;
import com.post.domain.PostVO;

public interface BoardMapper {
	int insertBoard(BoardVO b);

	List<BoardVO> getUserBoard(int midx);
	
	String selectNormalBoard(int midx);
	PostVO getPost(int pIdx);
	
	String randomMidx(); //랜덤 midx 추출하기
}
