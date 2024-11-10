//package com.example.demo.security1.filter;
//
//import java.io.IOException;
//
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//// OncePerRequestFilter 상속받고 doFilterInternal 메소드 구현
//public class ApliCheckFilter extends OncePerRequestFilter{
//
//	// 매개변수 
//	// request: 사용자가 보낸 요청 메세지를 담은 객체
//	// response: 사용자에게 전송할 응답메세지를 담을 객체
//	// filterChain: 인증 과정에서 사용되는 필터체인
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//		System.out.println("ApliCheckFilter...............");
//		System.out.println("ApliCheckFilter...............");
//		System.out.println("ApliCheckFilter...............");
//		
//		// 필터의 다음 단계로 넘어가기
//		filterChain.doFilter(request, response);
//	}
//
//}
