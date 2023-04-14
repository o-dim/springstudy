package com.gdu.myapp03.service;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;


public class FourthServiceImpl implements IFourthService {

	@Override
	public ResponseEntity<byte[]> display(String path, String filename) {
		
		
		try {
			// path와 filename을 이용하여 File 객체 만들기 
			File file = new java.io.File(path, filename);
			
			// 파일 객체를 byte 개열로 복사하기 
			byte[] b = FileCopyUtils.copyToByteArray(file);
			return new ResponseEntity<byte[]>(b, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
