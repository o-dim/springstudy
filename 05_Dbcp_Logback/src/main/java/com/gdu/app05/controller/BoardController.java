package com.gdu.app05.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app05.domain.BoardDTO;
import com.gdu.app05.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list.do")
	public String list(Model model) {
		List<BoardDTO> list = boardService.getBoardList();
		LOGGER.debug(list.toString()); // 목록확인
		model.addAttribute("boardList", list);
		return "board/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/add.do")
	public String add(BoardDTO board) {
		LOGGER.debug(board.toString()); // 파라미터 확인
		LOGGER.debug(boardService.addBoard(board) + ""); // 상세결과 확인
		boardService.addBoard(board);
		return "redirect:/board/list.do";
	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="board_no", required = false, defaultValue = "0") int board_no) {
		LOGGER.debug(board_no + "");
		LOGGER.debug(boardService.removeBoard(board_no) + ""); // 결과 확인
		return "redirect:/board/list.do";
	}
	
}
