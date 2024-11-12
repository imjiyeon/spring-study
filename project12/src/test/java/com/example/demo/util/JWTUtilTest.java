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

		// 위에서 생성한 토큰을 입력
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MzE0MTg3NzIsImV4cCI6MTczMTQxODgzMiwic3ViIjoidXNlciJ9.dyySUNeo96C5EYJNGO5bCQoAL-IylzyNzu7I0nPF0r4";

		// 토큰에서 아이디 추출
		String id = jwtUtil.validateAndExtract(token);
		
		System.out.println("아이디 추출: " + id);
	}
	// 유효기간을 1분으로 설정하고, 다시 토큰 생성
	// 1분 후에 에러가 발생되는지 확인
	// 토큰 유효기간이 지나면 인증 실패됨

}
