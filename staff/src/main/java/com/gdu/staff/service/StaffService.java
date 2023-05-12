package com.gdu.staff.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.gdu.staff.domain.StaffDTO;

public interface StaffService {

	public ResponseEntity<List<StaffDTO>> getStaffList();

	public String addStaff1(HttpServletRequest request);
	
	public ResponseEntity<StaffDTO> getStaffByNo(HttpServletRequest request);
}
