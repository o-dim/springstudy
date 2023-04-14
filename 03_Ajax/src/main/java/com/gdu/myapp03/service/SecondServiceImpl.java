package com.gdu.myapp03.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.gdu.myapp03.domain.BmiVO;

public class SecondServiceImpl implements ISecondService {

	/*
	@Override
	public BmiVO execute1(HttpServletRequest request, HttpServletResponse response) {
		
		 * try {
		 
			// bmi, obesity 넘기기
			double weight = Double.parseDouble(request.getParameter("weight"));
			double height = Double.parseDouble(request.getParameter("height"));
			
			double bmi = weight / height * height;
			String obesity = null;
			
			if(bmi < 18.5) {
				obesity = "저체중";
			} else if(bmi < 24.9) {
				obesity = "정상";
			} else if (bmi < 29.9) {
				obesity = "과체중";
			} else {
				obesity = "비만";
			}
			
			return new BmiVO(weight, height, bmi, obesity);
			
		} catch (NumberFormatException e) {
			try {
				response.setContentType("text/plain; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("몸무게와 키 입력을 확인하세요."); // $.ajax의 error로 넘기는 예외 메세지
				out.flush();
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public Map<String, Object> execute2(BmiVO bmiVO) {
		double weight = bmiVO.getWeight();
		double height = bmiVO.getHeight() / 100;

		double bmi = weight / height * height;
		String obesity = null;
		if(bmi < 18.5) {
			obesity = "저체중";
		} else if(bmi < 24.9) {
			obesity = "정상";
		} else if (bmi < 29.9) {
			obesity = "과체중";
		} else {
			obesity = "비만";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bmi", bmi);
		map.put("obesity", obesity);
		return map;

	}
	*/
	@Override
	public ResponseEntity<BmiVO> execute1(HttpServletRequest request){
		try {
			double weight = Double.parseDouble(request.getParameter("weight"));
			double height = Double.parseDouble(request.getParameter("height"));
			
			double bmi = weight / height * height;
			String obesity = null;
			
			if(bmi < 18.5) {
				obesity = "저체중";
			} else if(bmi < 24.9) {
				obesity = "정상";
			} else if (bmi < 29.9) {
				obesity = "과체중";
			} else {
				obesity = "비만";
			}
			return new ResponseEntity<BmiVO>(new BmiVO(weight, height, bmi, obesity), HttpStatus.OK);
			
		} catch (Exception e) {
			BmiVO bmiVO = null;
			return new ResponseEntity<BmiVO>(bmiVO, HttpStatus.INTERNAL_SERVER_ERROR);
			//httpStatus가 500이므로 $.ajax의 error에서 처리가 됨
		}
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> execute2(BmiVO bmiVO) {
		
		double height = bmiVO.getHeight() / 100;
		double weight = bmiVO.getWeight();
		
		if (height == 0 || weight == 0) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST); // 응답 코드가 정상이 아니므로 $.ajax에 error로 전달
		}
		double bmi = weight / height * height;
		String obesity = null;
		
		if(bmi < 18.5) {
			obesity = "저체중";
		} else if(bmi < 24.9) {
			obesity = "정상";
		} else if (bmi < 29.9) {
			obesity = "과체중";
		} else {
			obesity = "비만";
		}
		
		// 실제 응답할 데이터
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bmi", bmi);
		map.put("obesity", obesity);
		
		// 응답 헤더(Content-Type)
		MultiValueMap<String, String> header = new HttpHeaders();
		header.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		return new ResponseEntity<Map<String,Object>>(map, header, HttpStatus.OK);
		
	}

}
