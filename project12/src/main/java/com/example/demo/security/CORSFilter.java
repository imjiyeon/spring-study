package com.example.demo.security;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * CORS(Cross-Origin Resource Sharing) 문제를 해결하기 위한 필터 클래스
 * CORS: 다른 도메인에서 리소스를 요청할 때 발생하는 보안 문제
 * 모든 도메인에서 오는 요청을 허용하도록 설정
 * */

// @Order: 필터의 우선순위를 가장 높게 설정. 다른 필터들보다 먼저 실행됨
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter extends OncePerRequestFilter {

	// FilterChain: 다른 필터들을 호출하기 위한 객체
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// CORS 관련 헤더를 응답 메세지에 추가
		response.setHeader("Access-Control-Allow-Origin", "*"); // 모든 도메인에서의 요청을 허용
		response.setHeader("Access-Control-Allow-Credentials", "true"); // 자격 증명을 포함한 요청을 허용
		response.setHeader("Access-Control-Allow-Methods", "*"); // 모든 HTTP 메서드를 허용
		response.setHeader("Access-Control-Max-Age", "3600"); // 요청 결과를 3600초(1시간) 동안 캐시
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, Key, Authorization"); // 클라이언트가 요청할 수 있는 헤더 지정

		// HTTP 메서드가 OPTIONS일 경우 프리플라이트 요청은 200 OK 코드로 응답
		// 프리플라이트 요청: 클라이언트가 실제 요청을 보내기 전에 서버가 허용하는 메서드, 헤더 등을 확인하는 것
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			// 다른 요청(GET,POST,PUT,DELETE)는 그대로 처리 
			filterChain.doFilter(request, response);
		}

	}
}