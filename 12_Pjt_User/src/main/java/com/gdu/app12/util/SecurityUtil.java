package com.gdu.app12.util;

import java.security.MessageDigest;
import java.util.Iterator;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
	
	// 크로스 사이트 스크립팅 (Cross Site Scripting) 방지하기
	public String preventXSS(String str) {
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");
		return str;
	}
	
	// 인증코드 반환하기
	public String getRandomString(int count, boolean letters, boolean numbers) {
		return RandomStringUtils.random(count, letters, numbers);
	}
	
	// SHA-256 암호화하기
	public String getSha256(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] b = messageDigest.digest(); // 암호화된 32바이트 크기의 byte 배열 b가 생성된다
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < b.length; i++) {
			sb.append(String.format("%2X", b[i]));
		}
		return sb.toString();
	}
}
