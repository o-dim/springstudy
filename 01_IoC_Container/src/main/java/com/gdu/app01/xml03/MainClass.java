package com.gdu.app01.xml03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml03/app-context.xml");
		
		Board board = ctx.getBean("board", Board.class);
		
		System.out.println("title : " + board.getTitle());
		System.out.println("-- content --");
		System.out.println(board.getContent());
		System.out.println("-- writer -- ");
		System.out.println(board.getWriter().getName());
		System.out.println("writer id : " + board.getWriter().getId());
		
		ctx.close();
	}

}
