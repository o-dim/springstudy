package com.gdu.staff.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.mapper.StaffMapper;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffMapper staffMapper;
	
	/*
	 * @Override
	 * public List<StaffDTO> getStaffList(){
	 * 		List<StaffDTO> staffList = staffmapper.getStaffList();
			return staffList;
	 * }
	 * 
	 * 
	 * */
	@Override
	public ResponseEntity<List<StaffDTO>> getStaffList() {
		List<StaffDTO> staffList = staffMapper.getStaffList();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<List<StaffDTO>>(staffList, header, HttpStatus.OK);
	}
	
	@Override
	public String addStaff1(HttpServletRequest request) {
		try {
			String sno = request.getParameter("sno");
			String name = request.getParameter("name");
			String dept = request.getParameter("dept");
			
			StaffDTO staffDTO = new StaffDTO();
			staffDTO.setSno(sno);
			staffDTO.setName(name);
			staffDTO.setDept(dept);
			staffMapper.addStaff(staffDTO);
			return "사원 등록이 성공했습니다";
			
		} catch (Exception e) {
			return "사원 등록에 실패하였습니다";
		}
	}
	
	@Override
	public ResponseEntity<StaffDTO> getStaffByNo(HttpServletRequest request) {
		HttpHeaders header = new HttpHeaders();
		try {
			StaffDTO staff = staffMapper.getStaffByNo(Integer.parseInt(request.getParameter("query")));
			header.setContentType(MediaType.APPLICATION_JSON);
			return new ResponseEntity<StaffDTO>(staff, header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
