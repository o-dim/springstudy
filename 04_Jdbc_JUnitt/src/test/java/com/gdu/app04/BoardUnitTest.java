package com.gdu.app04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.app04.domain.BoardDTO;
import com.gdu.app04.repository.BoardDAO;

// JUnit4
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardUnitTest {
	// DAO 메소드 단위로 Unit Test 진행하기 위해서 BoardDAO 타입의 Bean이 필요하다
	@Autowired
	private BoardDAO boardDAO;
	
	// BoardUnitTest 클래스를 실행할 때 org.slf4j.Logger를 동작시킨다
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardUnitTest.class);

	
	// 테스트 메소드
	@Test
	public void a1삽입테스트() {
		BoardDTO board = new BoardDTO(1, "제목", "내용", "작성자", null, null);
		assertEquals(1, boardDAO.insertBoard(board)); // boardDAO.insertBoard(board)가 1이면 성공 !
	}
	
	@Test
	public void a2상세테스트() {
		int board_no = 1;
		BoardDTO board = boardDAO.selectBoardByNo(board_no);
		// System.out.println("a2 - " + board); // log 기능으로 대체할예정
		LOGGER.info(board.toString());
		assertNotNull(board);
	}
	
	@Test
	public void a3수정테스트() {
		BoardDTO board = new BoardDTO(1, "[수정]제목", "[수정]내용", null, null, null);
		assertEquals(1, boardDAO.updateBoard(board)); // 결과가 1이면 테스트 성공
	}
	
	@Test
	public void a4상세테스트() {
		int board_no = 1;
		BoardDTO board = boardDAO.selectBoardByNo(board_no);
		System.out.println("a4 - " + board); // log 기능으로 대체할예정
		assertNotNull(board);
	}
	
	@Test
	public void a5목록테스트() {
		List<BoardDTO> list = boardDAO.selectBoardList();
		System.out.println("a5 - " + list);
		assertEquals(1, list.size());
	}
	
	public void a6삭제테스트() {
		int board_no = 1;
		assertEquals(1, boardDAO.removeBoard(board_no));
		
	}
	
}
