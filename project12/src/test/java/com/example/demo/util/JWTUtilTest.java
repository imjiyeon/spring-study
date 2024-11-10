package com.example.demo.util;

import org.junit.jupiter.api.Test;

import com.example.demo.security.JWTUtil;

public class JWTUtilTest {

	
	@Test
	public void 토큰생성() throws Exception {
		
		JWTUtil jwtUtil = new JWTUtil();
		
		String id = "user";
		
		String token = jwtUtil.generateToken(id);
		
		System.out.println("토큰: " + token);
	}
	
	@Test
	public void 토큰검증() throws Exception {
		
		JWTUtil jwtUtil = new JWTUtil();
		
		String id = "user";
		
		String token = jwtUtil.generateToken(id);
		
		System.out.println("토큰: " + token);
		
		String resultId = jwtUtil.validateAndExtract(token);
		
		System.out.println("아이디 추출: " + resultId);
	}

}
