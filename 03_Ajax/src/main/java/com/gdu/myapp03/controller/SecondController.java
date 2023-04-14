package com.gdu.myapp03.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.myapp03.domain.BmiVO;
import com.gdu.myapp03.service.ISecondService;

@Controller
public class SecondController {
	
	private ISecondService secondService;
	
	@Autowired // 생성자에서 @Autowired는생략할 수 있다
	public SecondController(ISecondService secondService) {
		super();
		this.secondService = secondService;
	}
	
	@ResponseBody
	@GetMapping(value = "/second/bmi1", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BmiVO> bmi1(HttpServletRequest request){
		return secondService.execute1(request);
	}
	
	@ResponseBody
	@GetMapping(value = "second/bmi2") // produces가 없음에 주의합시다 (반환객체 ResponseEntity에 Content-Type을 작성해서 보낸다)
	public ResponseEntity<Map<String, Object>> bmi2(BmiVO bmiVO){
		return secondService.execute2(bmiVO);
	}
	/*
	@ResponseBody // 이게 없으면 view 이름으로 해석하려고 함
	@GetMapping(value ="/second/bmi1", produces = MediaType.APPLICATION_JSON_VALUE )
	public BmiVO bmi1(HttpServletRequest request, HttpServletResponse response) {
		return secondService.execute1(request, response);
	}
	
	@ResponseBody
	@GetMapping(value = "/second/bmi2", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> bmi2(BmiVO bmiVO){
		return secondService.execute2(bmiVO);
	}
	*/
	
}
