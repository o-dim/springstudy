package com.gdu.app12.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app12.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/agree.form")
	public String agreeJsp() {
		return "user/agree";
	}
	
	@GetMapping("/join.form")
	public String joinJsp(@RequestParam(value = "location", required = false) String location
						, @RequestParam(value = "event", required = false) String event
						, Model model) {
		model.addAttribute("location", location);
		model.addAttribute("event", event);
		return "user/join";
	}
	
	@ResponseBody
	@GetMapping(value = "/verifyId.do", produces = "application/json")
	public Map<String, Object> verifyId(@RequestParam("id") String id) {
		return userService.verifyId(id);
	}
	
	@ResponseBody
	@GetMapping(value="/verifyEmail.do", produces = "application/json")
	public Map<String, Object> verifyEmail(@RequestParam("email") String email){
		return userService.verifyEmail(email);
	}
	
	@ResponseBody
	@GetMapping(value="/sendAuthCode.do", produces = "application/json")
	public Map<String, Object> sendAuthCode(@RequestParam("email") String email){
		return userService.sendAuthCode(email);
	}
	
	@PostMapping("/join.do")
	public void join(HttpServletRequest request, HttpServletResponse response) {
		userService.join(request, response);
	}
	
	@PostMapping("/login.do")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		userService.login(request, response);
	}
	
	// 로그인여부 확인필요
	@PostMapping("/logout.do")
	public String requiredLogin_logout(HttpServletRequest request, HttpServletResponse response) {
		userService.logout(request, response);
		return "redirect:" + request.getContextPath() + "/index.do";
	}
	
	// 로그인 여부 확인필요
	@GetMapping("leave.do")
	public void requiredLogin_leave(HttpServletRequest request, HttpServletResponse response) {
		userService.leave(request, response);
	}
}
