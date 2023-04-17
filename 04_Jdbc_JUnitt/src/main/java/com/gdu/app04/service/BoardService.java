package com.gdu.app04.service;

import java.util.List;

import com.gdu.app04.domain.BoardDTO;

public interface BoardService {
	public List<BoardDTO> getBoardList();
	public BoardDTO getBoardbyNo(int board_no);
	public int addBoard(BoardDTO board);
	public int modifyBoard(BoardDTO board);
	public int removeBoard(int board_no);
}
