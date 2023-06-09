package com.gdu.myapp03.service;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gdu.myapp03.domain.Contact;

public class ThirdServiceImpl implements IThirdservice {

	@Override
	public ResponseEntity<Contact> execute1(Contact contact) {
		// 이름 또는 전화번호가 공백인 경우 $.ajax 에서 error 처리 
		if (contact.getName().isEmpty() || contact.getTel().isEmpty()) {
			return new ResponseEntity<Contact>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, String>> execute2(Map<String, String> map) {
		// 이름이 공백이거나 전화번호의 글자수가 3글자 이하인 경우 $.ajax의 error 처리
		if(map.get("name").isEmpty() || map.get("tel").length() <= 3) {
			return new ResponseEntity<Map<String,String>>(HttpStatus.BAD_REQUEST);
		}
		// 이름은 그대로, 전화번호에 하이픈(-)이 포함되어 있으면 모두 제거 후 돌려보내기($.ajax의 success로 전달)
		StringBuffer sb = new StringBuffer();
		sb.append(map.get("tel").split("-"));
		return new ResponseEntity<Map<String,String>>(map.get("name"), sb.toString());

	}

}
