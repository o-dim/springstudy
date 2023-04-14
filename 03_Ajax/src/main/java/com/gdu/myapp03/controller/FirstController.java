package com.gdu.myapp03.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.myapp03.domain.Person;
import com.gdu.myapp03.service.IFirstService;


@Controller
public class FirstController {
	// FirstServiceImpl을 Spring Container에 Bean으로 등록시켜보자
	// Spring Container에 등록된 firstService Bean을 아래 필드 firstService에 주입해보자.
	@Autowired
	private IFirstService firstService;
	
	@ResponseBody // 메소드의 반환값을 Jsp이름으로 해석하지 않는다.
	@GetMapping(value="/first/ajax1", produces = "appliction/json") // produces : 응답할 데이터의 MIME TYPE
	public Person ajax1(HttpServletRequest request, 
						HttpServletResponse response) { // "Jackson 라이브러리"가 반환값 Person 객체를 자동으로 JSON 데이터로 변환한다.
		return firstService.execute1(request, response);
	}
	@ResponseBody
	@GetMapping(value="/first/ajax2", produces = "application/json")
	public Map<String, Object> ajax2(@RequestParam("name") String name, @RequestParam("age") int age){
		return firstService.execute2(name, age); // Jackson 라이브러리가 반환값 map을 자동으로 JSON 데이터로 변환한다.
	}
	@ResponseBody
	@GetMapping(value="/first/ajax3", produces = "application/json")
	public Map<String, Object> ajax3(@RequestParam("name") String name, @RequestParam("age") int age){
		return firstService.execute2(name, age);
	}
}