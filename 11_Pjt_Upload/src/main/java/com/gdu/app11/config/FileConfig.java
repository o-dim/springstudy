package com.gdu.app11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileConfig {
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver MultipartResolver = new CommonsMultipartResolver();
		MultipartResolver.setDefaultEncoding("UTF-8");
		MultipartResolver.setMaxUploadSize(1024 * 1024 * 100); // 전체 첨부파일 크기 100mb
		MultipartResolver.setMaxUploadSizePerFile(1024 * 1024 * 10); // 첨부파일 하나의 크기 10mb
		return MultipartResolver;
	
	}
}
