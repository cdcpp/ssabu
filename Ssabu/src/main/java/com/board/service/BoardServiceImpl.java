package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.domain.BoardVO;
import com.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	 BoardMapper bMapper;
	
	@Override
	public int insertBoard(BoardVO b) {
		// TODO Auto-generated method stub
		return bMapper.insertBoard(b);
	}

	@Override
	public List<BoardVO> getUserBoard(int midx) {
		// TODO Auto-generated method stub
		return bMapper.getUserBoard(midx);
	}

	@Override
	public String selectNormalBoard(int midx) {
		// TODO Auto-generated method stub
		return bMapper.selectNormalBoard(midx);
	}

	@Override
	public String randomMidx() {
		// TODO Auto-generated method stub
		return bMapper.randomMidx();
	}

}
