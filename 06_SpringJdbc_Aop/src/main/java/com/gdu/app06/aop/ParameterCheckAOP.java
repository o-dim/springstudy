package com.gdu.app06.aop;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
// @EnableAspectJAutoProxy DBConfig에서 이미 호출했기에 필요X
public class ParameterCheckAOP {
	
	// 포인트컷(어떤 메소드에 어드바이스(AOP 동작)를 적용할 것인가?) - ParamCheck로 끝나는애들
	@Pointcut("execution(* com.gdu.app06.controller.*Controller.*ParamCheck(..))")
	public void setPointCut() {
	}
	
	// 어드바이스 (포인트컷에서 실제로 동작할 작업 : 파라미터들의 값을 확인)
	// 파라미터를 콘솔에 출력하기 위한 LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger(ParameterCheckAOP.class);
	@After("setPointCut()")
	public void paramLogging(Joinpoint joinPoint) throws Throwable{
		// 모든 파라미터가 저장된 HttpServletRequest 가져오기
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		
		// HttpServletRequest -> Map으로 변환하기
		// 파라미터가 Map의 key로 변환. Map의 Key는 반복문으로 순회가 가능하다!
		Map<String, String[]> map = request.getParameterMap();
		
		// 콘솔에 출력할 형태 만들기
		// [파라미터명=값]
		String str = "";
		if(map.isEmpty()) {
			str += "[No Parameter]";
		} else {
			for (Entry<String, String[]> entry : map.entrySet()) {
				str += "[" + entry.getKey() + "=" + Arrays.toString(entry.getValue()) + "]";
			}
		}
		// 어드바이스 실행 
		// 치환문자 {}
		LOGGER.debug("{} {} {}", request.getMethod(), request.getRequestURI(), str);
		
	}
}
