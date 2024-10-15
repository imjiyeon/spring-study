package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * 첨부 파일의 경로를 맵핑하기 위한 설정 클래스
 * */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${filepath}")
	String filepath; //첨부 폴더 경로

	//스프링 보안문제로 외부폴더에 바로 접근할수 없음 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {	
		//폴더와 상대경로 맵핑
		registry.addResourceHandler("/uploadfile/**").addResourceLocations("file:/" + filepath);
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

}