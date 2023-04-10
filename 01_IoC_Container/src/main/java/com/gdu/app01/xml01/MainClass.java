package com.gdu.app01.xml01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// src/main/resources/xml01디렉터리에 있는 app-context.xml 파일에 정의된 bean을 쓸게용
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml01/app-context.xml");
		
		// bean 중에서 student라는 id를 가진 bean 을 주세용!
		Student haksaeng = ctx.getBean("student", Student.class); // (Student)ctx.getBean("student")
		
		// haksaeng 의 calculator를 이용한 메소드를 호출함
		haksaeng.getCalculator().add(222, 555);
		haksaeng.getCalculator().minus(33, 3);
		haksaeng.getCalculator().mul(3, 6);
		haksaeng.getCalculator().div(60, 2);
		
		// 사용한 자원을 반납해야해용
		ctx.close();
		// 사실 생략 가능함^^
	}

}
