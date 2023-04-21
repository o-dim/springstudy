package com.gdu.app07.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gdu.app07.domain.BoardDTO;

@Service
public interface BoardService {
	public List<BoardDTO> getBoardList();
	public BoardDTO getBoardByNo(HttpServletRequest request);
	public int addBoard(HttpServletRequest request);
	public int modifyBoard(HttpServletRequest request);
	public int deleteBoard(HttpServletRequest request);
	public void testTx();
}
