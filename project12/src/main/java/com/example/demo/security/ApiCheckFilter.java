package com.example.demo.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONObject;


// 요청이 들어오면 JWT 토큰이 유효한지 확인하는 필터

// OncePerRequestFilter 상속받고 doFilterInternal 메소드 구현
public class ApiCheckFilter extends OncePerRequestFilter {

	// 토큰 검사가 필요한 URL 패턴 목록
	// /login, /register 검사 X
	String[] patternArr = { "/board/*", "/member/*" };

	// 패턴 검사기
	AntPathMatcher antPathMatcher;
	
	// JWT 유틸 (토큰 생성 및 검사)
	JWTUtil jwtUtil;
	
	// 사용자 인증 서비스
	UserDetailsService userDetailsService;

	public ApiCheckFilter(UserDetailsService userDetailsService) {
		this.antPathMatcher = new AntPathMatcher();
		this.jwtUtil = new JWTUtil();
		this.userDetailsService = userDetailsService;
	}

	// 매개변수
	// request: 사용자가 보낸 요청 메세지를 담은 객체
	// response: 사용자에게 전송할 응답메세지를 담을 객체
	// filterChain: 인증 과정에서 사용되는 필터체인

	// API 요청마다 호출되는 함수. 토큰이 유효한지 확인
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 요청 URL이 패턴과 일치하는 경우에 토큰 검사
		// 예시. 현재경로: /board/list 패턴: /board/* 또는 /member/*
		
		for (String pattern : patternArr) {
			boolean result = antPathMatcher.match(pattern, request.getRequestURI());
			if (result == true) {

				System.out.println("ApiCheckFilter...............");
				System.out.println("ApiCheckFilter...............");
				System.out.println("ApiCheckFilter...............");

				// 헤더에서 토큰을 꺼내서 검사
				boolean checkHeader = checkAuthHeader(request);

//				if(checkHeader){
//					filterChain.doFilter(request, response);
//					return;
//				} else {
//					// 토큰이 유효하지 않다면 필터체인 종료
//					return;
//				}
				
				if (checkHeader) {
					
					// 인증서비스로 로그인을해서 인증객체(User)를 생성
					// 인증객체를 컨테이너에 저장
					// 이객체는 나중에 Principal라는 이름으로 사용됨
					// 나중에 로그인한 사용자의 아이디가 필요한 경우에 사용
					String username = getUserId(request);
					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					if (userDetails != null) {
						UsernamePasswordAuthenticationToken authentication = 
								new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						// 인증 객체를 SecurityContext에 저장하여 이후 필터체인에서 인증 상태 유지
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
					
					// 토큰이 유효하면 필터의 다음 단계로 넘어가서 인증을 이어가기
					filterChain.doFilter(request, response);
					return;
				} else {
					// 토큰 검사에 실패했는데도, 200코드 정상코드가 반환된다
					// 정상적으로 처리하기 위해 403 메세지를 만들어서 반환한다
					
					// 토큰이 유효하지 않으면 403 에러메세지가 전송되고 필터체인이 종료됨
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					response.setContentType("application/json;charset=utf-8");
					JSONObject json = new JSONObject();
					json.put("code", "403");
					json.put("message", "FAIL CHECK API TOKEN");

					PrintWriter out = response.getWriter();
					out.print(json);
					return; // 인증 종료
				}

			}
		}

		filterChain.doFilter(request, response);
	}

	// 헤더에 담긴 토큰이 유효한지 확인하는 메소드
	private boolean checkAuthHeader(HttpServletRequest request) {

		// 요청 메세지에서 인증키 꺼내기
		String authHeader = request.getHeader("Authorization");

		// 값이 있는지 확인
		if (StringUtils.hasText(authHeader)) {
//			System.out.println("Authorization: " + authHeader);
//			if (authHeader.equals("1234")) {
//				// 인증에 성공했으면 true 반환
//				return true;
//			}
			
			// 토큰에 사용자 아이디가 포함되어 있으면 통과
			String id;
			try {
				id = jwtUtil.validateAndExtract(authHeader);
				if(id.length() > 0) {
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

	// 토큰에서 사용자 아이디만 추출하는 메소드
	private String getUserId(HttpServletRequest request) {

		String authHeader = request.getHeader("Authorization");

		if (StringUtils.hasText(authHeader)) {

			try {
				String id = jwtUtil.validateAndExtract(authHeader);
				return id;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}