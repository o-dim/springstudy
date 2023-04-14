package com.gdu.myapp03.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.myapp03.domain.Person;

public class FirstServiceImpl implements IFirstService {
	// FirstServiceImpl을 Spring Container에 Bean으로 등록시켜보자
	public Person execute1(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 예외 발생 시 예외 메세지를 화면으로 전달하기
			String name = request.getParameter("name");
			name = name.isEmpty() ? "홍길동" : name;   // 사용자가 입력한 name이 없으면 빈 문자열이 전달된다.
			
			String strAge = request.getParameter("age");
			strAge = strAge.isEmpty() ? "0" : strAge;  // 사용자가 입력한 age가 없으면 빈 문자열이 전달된다.
			int age = Integer.parseInt(strAge);
			
			// 0 ~ 100 범위를 벗어난 경우 예외 발생시키기
			if (age < 0 || age > 100) {
				throw new RuntimeException(age + "살은 잘못된 나이입니다.");
			}
			return new Person(name, age); // $.ajax의 success로 넘기는 값
			
		} catch (Exception e) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(e.getMessage()); // $.ajax의 error로 넘기는 예외 메세지
				out.flush();
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return null;
		}
	}
	@Override
	public Map<String, Object> execute2(String name, int age){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("age", age);
		return map;
	}
	@Override
	public Map<String, Object> execute3(Person person){
		return null;
	}

}
