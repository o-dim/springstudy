package com.gdu.app04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app04.domain.BoardDTO;
import com.gdu.app04.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardDTO> getBoardList() {
		return boardDAO.selectBoardList();
	}

	@Override
	public BoardDTO getBoardbyNo(int board_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBoard(int board_no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
