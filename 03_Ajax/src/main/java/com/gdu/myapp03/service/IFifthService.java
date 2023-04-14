package com.gdu.myapp03.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface IFifthService {
	 public ResponseEntity<String> papago(HttpServletRequest request);
}
