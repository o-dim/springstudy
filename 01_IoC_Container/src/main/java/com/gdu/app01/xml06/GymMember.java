package com.gdu.app01.xml06;

import java.util.List;

public class GymMember {
	private String name;
	private List<String> cources;
	private BMICalculator bmiCalc;
	public GymMember(String name, List<String> cources, BMICalculator bmiCalc) {
		super();
		this.name = name;
		this.cources = cources;
		this.bmiCalc = bmiCalc;
	}
	
	public void memberInfo() {
		System.out.println("이름 : " + name);
		for (int i = 0; i < cources.size(); i++) {
			System.out.println((i + 1) + "번째 종목 : " + cources.get(i));
		}
		bmiCalc.bmiInfo();
	}
	
}
