package com.gdu.app11.util;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;
import java.util.regex.Matcher;

import org.springframework.stereotype.Component;

@Component
public class MyFileUtil {
	
	// 경로 구분자
	private String sep = Matcher.quoteReplacement(File.separator);
	
	// String path 만들기
	public String getPath() {
		LocalDate date = LocalDate.now();
		
		return "/Users/mina/storage" + sep + date.getYear() + sep + String.format("%2d", date.getMonthValue()) + sep +  String.format("%2d", date.getDayOfMonth());
	}
	
	// String fileSystemName 만들기
	public String getFileSystemName(String originName) {
		
		// 원래 첨부 파일의 확장자꺼내기
		String extName = null;
		
		// 확장자에 마침표가 포함된 예외 상황 처리
		if(originName.endsWith("tar.gz")) {
			extName = "tar.gz";
		} else {
			// split(정규식)
			// 정규식에서 마침표는 모든 문자를 의미하므로 이스케이프 처리하거나 문자 클래스로 처리한다.
			String[] array = originName.split("\\.");
			extName = array[array.length - 1];
		}
		
		// 결과 반환
		// UUID.extName
		return UUID.randomUUID().toString().replace("-", "") + "." + extName;
	}
}
