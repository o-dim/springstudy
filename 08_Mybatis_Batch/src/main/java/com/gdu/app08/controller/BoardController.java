package com.gdu.app08.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app08.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list.do")
	public String list(Model model) { // forward 할 정보는 Model에 저장한다 
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/list";
	}
	
	@GetMapping("/detail.do")
	public String detail(HttpServletRequest request, Model model) {
		model.addAttribute("b", boardService.getBoardByNo(request));
		return "board/detail";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/add.do")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		boardService.addBoard(request, response);
	}

	@PostMapping("/modify.do")
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		boardService.modifyBoard(request, response);
	}
	
	@PostMapping("/remove.do")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		boardService.deleteBoard(request, response);
	}
	
	@PostMapping("/removeList.do")
	public void removeList(HttpServletRequest request, HttpServletResponse response) {
		boardService.removeBoardList(request, response);
	}
	
}