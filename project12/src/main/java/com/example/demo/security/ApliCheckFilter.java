package com.example.demo.security;

import java.io.IOException;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 요청이 들어오면 토큰이 유효한지 확인하는 필터

// OncePerRequestFilter 상속받고 doFilterInternal 메소드 구현
public class ApliCheckFilter extends OncePerRequestFilter{

	// 검사가 필요한 URL 목록
	String[] patternArr = { "/board/*", "/member*" };
	
	// 
	AntPathMatcher antPathMatcher;
	
	public ApliCheckFilter() {
		this.antPathMatcher = new AntPathMatcher();
	}

	// 매개변수 
	// request: 사용자가 보낸 요청 메세지를 담은 객체
	// response: 사용자에게 전송할 응답메세지를 담을 객체
	// filterChain: 인증 과정에서 사용되는 필터체인
	
	// 토큰이 유효한지 확인하는 메소드
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 해당 경로가 검사가 필요한 API라면 ApliCheckFilter 필터 실행
		for (String pattern : patternArr) {
			boolean result = antPathMatcher.match(pattern, request.getRequestURI());
			if(result == true) {
				System.out.println("ApliCheckFilter...............");
				System.out.println("ApliCheckFilter...............");
				System.out.println("ApliCheckFilter...............");
			}
		}
		
		// 필터의 다음 단계로 넘어가기
		filterChain.doFilter(request, response);
	}

}