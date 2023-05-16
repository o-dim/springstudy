package com.gdu.app12.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gdu.app12.mapper.UserMapper;
import com.gdu.app12.util.JavaMailUtil;
import com.gdu.app12.util.SecurityUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor // field에 @autowired처리
@Service
public class UserServiceImpl implements UserService {
	
	private UserMapper userMapper;
	private JavaMailUtil javaMailUtil;
	private SecurityUtil securityUtil;
	
	@Override
	public Map<String, Object> verifyId(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enableId", userMapper.selectUserById(id) == null && userMapper.selectSleepUserById(id) == null && userMapper.selectLeaveUserById(id) == null);
		return map;
	}
	
	@Override
	public Map<String, Object> verifyEmail(String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enableEmail", userMapper.selectUserByEmail(email) == null && userMapper.selectSleepUserByEmail(email) == null && userMapper.selectLeaveUserByEmail(email) == null);
		return map;
	}
	
	@Override
	public Map<String, Object> sendAuthCode(String email) {
		// 사용자에게 전송할 인증코드 여섯자리 만들기
		String authCode = securityUtil.getRandomString(6, true, true);
		
		// 사용자에게 메일 보내기
		javaMailUtil.sendJavaMail(email, "[서비스이름]인증코드를 입력해주세요.", "인증번호는 <strong>" + authCode + "</strong>입니다.");
		
		// 사용자에게 전송한 인증 코드를 join.jsp로 응답시키기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("authCode", authCode);
		return map;
	}
}
