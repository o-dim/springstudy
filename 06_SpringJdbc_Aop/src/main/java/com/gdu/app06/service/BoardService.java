package com.gdu.app06.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gdu.app06.domain.BoardDTO;

@Service
public interface BoardService {
	public List<BoardDTO> getBoardList();
	public BoardDTO getBoardByNo(int board_no);
	public int addBoard(BoardDTO board);
	public int modifyBoard(BoardDTO board);
	public int removeBoard(int board_no);
	
	public void testTx();
}
