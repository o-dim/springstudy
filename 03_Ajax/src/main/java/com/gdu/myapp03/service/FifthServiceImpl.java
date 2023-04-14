package com.gdu.myapp03.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;


public class FifthServiceImpl implements IFifthService {

	@Override
	public ResponseEntity<Map<String, String>> execute1(HttpServletRequest request) {
		request.setCharacterEncoding("UTF-8");
		String source = request.getParameter("source");
		String target = request.getParameter("target");
		String text = request.getParameter("text");
		
		// 클라이언트 아이디, 시크릿
		String clientId = "ccECUMZux1qa0r2tI9Kl";
		String clientSecret = "JZl4bul16d";
		
		// API 주소
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		URI url = new URI(apiURL);
		
		// HttpURLConnection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		// 요청 메소드
		con.setRequestMethod("POST");
	
		// 요청 헤더에 포함하는 내용
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		
		// papago api로 보내야하는 파라미터 (source, target, text)
		String params = "source=" + source + "&target=" + target + "&text=" + URLEncoder.encode(text, "UTF-8");
		
		// Papago Api로 파라미터를 보내기 위해서 출력 스트림 생성
		con.setDoOutput(true);
		DataOutputStream dos = new DataOutputStream(con.getOutputStream());
		
		// Papago Api로 파라미터 보내기
		dos.write(params.getBytes());
		dos.flush();
		dos.close();
		
		// Papago API로부터 번역결과 받아올 입력 스트림 생성
		BufferedReader reader = null;
		if(con.getResponseCode() == 200) {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		// Papago API로부터 번역 결과를 받아서 StringBuilder에 저장
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}                                
	}

}
