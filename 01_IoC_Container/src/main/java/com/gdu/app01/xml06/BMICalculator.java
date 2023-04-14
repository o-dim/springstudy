package com.gdu.app01.xml06;

public class BMICalculator {
	private double height;
	private double weight;
	private double bmi;
	private String health;
	private Calculator calc;
	public BMICalculator(double height, double weight, Calculator calc) {
		super();
		this.height = height;
		this.weight = weight;
		bmi = calc.div(weight, calc.mul(height, height));
		health = (bmi < 20) ? "저체중" : (bmi < 25) ? "보통" : "비만";
	}
	public void bmiInfo() {
		System.out.println("키 : " + height + "m");
		System.out.println("몸무게 : " + weight + "kg");
		System.out.println("BMI : " + bmi);
		System.out.println("건강 상태 : " + health);
	}
	
}
