package com.gdu.myapp03.service;

import org.springframework.http.ResponseEntity;

public interface IFourthService {
	public ResponseEntity<byte[]> display(String path, String filename);
}
