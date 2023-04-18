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
		return boardDAO.selectBoardByNo(board_no);
	}

	@Override
	public int addBoard(BoardDTO board) {
		return boardDAO.insertBoard(board);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		return boardDAO.updateBoard(board);
	}

	@Override
	public int removeBoard(int board_no) {
		return boardDAO.removeBoard(board_no);
	}

}
