package com.gdu.app06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardDTO> getBoardList() {
		return boardDAO.getBoardList();
	}

	@Override
	public BoardDTO getBoardByNo(int board_no) {
		return boardDAO.getBoardByNo(board_no);
	}

	@Override
	public int addBoard(BoardDTO board) {
		return boardDAO.addBoard(board);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		return boardDAO.modifyBoard(board);
	}

	@Override
	public int removeBoard(int board_no) {
		return boardDAO.removeBoard(board_no);
	}
	
	// AOP를 활용한 트랜잭션 처리 테스트
	@Override
	public void testTx() {
		// 두 개 이상의 삽입, 수정, 삭제가 하나의 서비스에서 진행되는 경우 트랜잭션 처리가 필요하다
		
		// 성공하는 작업
		boardDAO.addBoard(new BoardDTO(0, "트랜잭션제목", "트랜잭션내용", "트랜잭션작성자", null, null));
		
		// 실패하는 작업
		boardDAO.addBoard(new BoardDTO()); // title 칼럼은 not null인데 비워뒀으므로 exception이 발생하여 실패!
		
		// 트랜잭션 처리가 된다면 모든 insert가 실패해야한다.
		
	}

}
