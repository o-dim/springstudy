8package com.gdu.myapp03.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.myapp03.domain.Contact;
import com.gdu.myapp03.service.IThirdservice;

@Controller
public class ThirdController {
	
	// field 선언
	private IThirdservice thirdService;
	
	// 세 가지 방법으로 Bean을 불ㄱ억ㄷㄹㄴ다
	// 그 중 하나세
	@Autowired // setter method 형식의 자동 주입의 경우 @autowired 생략할 수 없다
	public void method(IThirdservice thirdService) { // Spring Container 에서 IThirdService 타입의 Bean을 찾아서 매개변수에 주입한다
		this.thirdService = thirdService;
	}
	
	@PostMapping(value = "/third/ajax1", produces = "application/json")
	public ResponseEntity<Contact> ajax1(@RequestBody Contact contact){
		// 요청 본문(request body)에 포함된 JSON 데이터를 Contact contact 객체에 저장해 주세요 . 
		return thirdService.execute1(contact);                                                                                               
	}
	
	@PostMapping(value = "/third/ajax2", produces = "application/json")
	public ResponseEntity<Map<String, String>> ajax2(){
		return thirdService.execute2(Map<String, String>);
	}
}
