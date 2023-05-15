package com.gdu.app12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class MvcController {
	@GetMapping("/")
	public String welcome() {
		return "/index";
	}
	
}
