package com.gdu.app05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/board")
public class BoardController {
	@GetMapping("/list.do")
	public String getlist() {
		return "board/list";
	}
}
