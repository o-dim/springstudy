package com.gdu.staff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.service.StaffService;

@Controller
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	// map으로 부를거면 꼭 @ResponseBody를 불러야함!
	//	@ResponseBody
	//	@GetMapping(value="/list.json", produces=MediaType.APPLICATION_JSON_VALUE)
	//	public List<StaffDTO> list(){
	// 		return staffService.getStaffList();
	
	@GetMapping("/list.json")
	public ResponseEntity<List<StaffDTO>> list() {
		return staffService.getStaffList();
	}
	
    @ResponseBody // text 반환이면 이거 쓰면 됨(아니면 jsp로 해석하니까)
	@PostMapping(value="/add.do", produces = "text/plain; charset=UTF-8")
	public String add(HttpServletRequest request) {
		return staffService.addStaff1(request);
	}
	
//	@GetMapping("query.json")
//	public ResponseEntity<StaffDTO> query(HttpServletRequest request) {
//		return staffService.getStaffByNo(request);
//	}
	
	@ResponseBody
	@GetMapping(value="/query.json", produces = "appliction/json") // produces : 응답할 데이터의 MIME TYPE
	public ResponseEntity<StaffDTO> query(HttpServletRequest request) {
		return staffService.getStaffByNo(request);
	}
	
}
