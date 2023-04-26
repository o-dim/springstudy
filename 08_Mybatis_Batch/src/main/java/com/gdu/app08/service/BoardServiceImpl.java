package com.gdu.app08.service;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app08.domain.BoardDTO;
import com.gdu.app08.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDTO> getBoardList() {
		return boardMapper.selectBoardList();
	}

	@Override
	public BoardDTO getBoardByNo(HttpServletRequest request) {
		// 파라미터 boardNo가 없으면 0으로 처리한다
		String strBoardNo = request.getParameter("boardNo");
		int boardNo = 0;
		if(strBoardNo != null && strBoardNo.isEmpty() == false) {
			boardNo = Integer.parseInt(strBoardNo);
		}
		return boardMapper.selectBoardByNo(boardNo);
	}

	@Override
	public void addBoard(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		// BoardDAO 로 전달할 BoardDTO를 만든다
		BoardDTO board = new BoardDTO();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		int addResult = boardMapper.insertBoard(board);		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if (addResult == 1) {
				out.println("alert('게시글이 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/board/list.do'");
			} else {
				out.println("alert('게시글이 등록되지 않았습니다.')");
				out.println("history.back()");
			}
			out.println("</script>");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyBoard(HttpServletRequest request, HttpServletResponse response) {
		BoardDTO board = new BoardDTO();
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		int modifyResult = boardMapper.insertBoard(board);		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if (modifyResult == 1) {
				out.println("alert('게시글이 수정되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/board/list.do'");
			} else {
				out.println("alert('게시글이 수정되지 않았습니다.')");
				out.println("history.back()");
			}
			out.println("</script>");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBoard(HttpServletRequest request, HttpServletResponse response) {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int deleteResult = boardMapper.deleteBoard(boardNo);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if (deleteResult == 1) {
				out.println("alert('게시글이 삭제되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/board/list.do'");
			} else {
				out.println("alert('게시글이 삭제되지 않았습니다.')");
				out.println("history.back()");
			}
			out.println("</script>");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void removeBoardList(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터 boardNoList
		String[] boardNoList = request.getParameterValues("boardNoList");
		int removeResult = boardMapper.deleteBoardList(Arrays.asList(boardNoList)); // String[] boardNoList를 ArrayList로 바꾸는 코드
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if(removeResult == boardNoList.length) {
				out.println("alert('선택된 모든 게시글이 삭제되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/board/list.do'");
			} else {
				out.println("alert('선택된 게시글이 삭제되지 않았습니다.')");
				out.println("history.back()");
			}
			out.println("</script>");
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getBoardCount() {
		int boardCount = boardMapper.selectBoardCount();
		String msg = "[" + LocalDateTime.now().toString() + "] 게시글 갯수는 " + boardCount + "개입니다.";
		System.out.println(msg);
	}

	
}
