package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;

public interface BoardService {
	int insertBoard(BoardVO b);

	List<BoardVO> getUserBoard(int midx);

	String selectNormalBoard(int midx);
	
	String randomMidx(); //랜덤 midx 추출하기
}
