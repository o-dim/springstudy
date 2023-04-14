package com.gdu.app02.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {
	@GetMapping("/post/detail.do")
	public String detail(HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		System.out.println("/post/detail.do");
		System.out.println("name : " + name + "age : " + age);
		
		// redirect 로 보내기 "redirect:이동경로"
		return "redirect:/post/list.do?name=" + URLEncoder.encode(name, "UTF-8") + "&age=" + age;
		
	}
	
	@GetMapping("/post/list.do")
	public String list(HttpServletRequest request,  // name, age 파라미터를 받게 되다
						Model model) {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		// /WEB-INF/views/post/list.jsp로 forward하고싶어!
		return "post/list";
	}
	@GetMapping("/post/detail.me")
	public String detailMe(HttpServletRequest request, 
							RedirectAttributes redirectAttributes) { // Redirect 할 때 속성( Attribute)를 전달하는 스프링 인터페이스
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		// Redirect 경로까지 전달되는 속성을 갖는 Flash Attribute 
		redirectAttributes.addFlashAttribute("name", name);
		redirectAttributes.addFlashAttribute("age", age);
		
		return "redirect:/post/list.me";
	}
	@GetMapping("/post/list.me")
	public String listMe() { // Flash Attribute 는 Redirect 경로까지 자동으로 전달되므로 별도의 코드가 필요없다
		return "post/list";
	}
}
