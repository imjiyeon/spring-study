package com.example.demo.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// API 로그인 필터 클래스
// 로그인 요청이 들어오면 아이디와 패스워드를 검증하여, JWT 토큰을 발급

public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

	// JWT 유틸
	JWTUtil jwtUtil;

	// 사용자 정보를 조회하기 위해 선언
	MemberService memberService;

	// 생성자
	// 로그인 URL 경로와 필요한 서비스 초기화
	public ApiLoginFilter(String defaultFilterProcessesUrl, MemberService memberService) {
		super(defaultFilterProcessesUrl);
		this.jwtUtil = new JWTUtil();
		this.memberService = memberService;
	}

	// 로그인 요청 시 아이디와 패스워드를 확인하는 메소드
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		System.out.println("ApiLoginFilter.....attempt.....");
		
		// 메세지 바디에서 로그인 데이터 추출
		String body = getBody(request);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> map = objectMapper.readValue(body, HashMap.class);

		String id = map.get("id");
		String password = map.get("password");

		if (id == null) {
			throw new BadCredentialsException("id cannot be null");
		}

		// 아이디와 패스워드를 기반으로 토큰 생성
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(id, password);

		// 인증매니저에 토큰을 전달
		// JWT와 같은 실제 토큰이 아니라, 인증매니저에게 인증을 시도하는 용도
		return getAuthenticationManager().authenticate(authToken);
	}

	// 요청 메세지에서 바디 데이터를 꺼내는 메소드
	public String getBody(HttpServletRequest request) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = request.getReader();
			char[] charBuffer = new char[128];
			int bytesRead;
			while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
				stringBuilder.append(charBuffer, 0, bytesRead);
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		return stringBuilder.toString();
	}

	// 인증을 성공적으로 완료한 후 호출하는 메소드
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		System.out.println("ApiLoginFilter.....success.....");
		System.out.println("인증결과: " + authResult);
		System.out.println("인증객체: " + authResult.getPrincipal());

		// 인증된 사용자의 ID 가져오기
		String id = authResult.getName();
		System.out.println("아이디: " + id);

		String token = null;
		try {
			// JWT 토큰 생성
			token = jwtUtil.generateToken(id);
			System.out.println(token);

			// 사용자 정보 조회
			MemberDTO member = memberService.read(id);

			// 응답 데이터 생성 (토큰과 사용자정보)
			HashMap<String, Object> data = new HashMap<>();
			data.put("token", token);
			data.put("user", member);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			// 객체를 JSON문자열로 변환하기 위해 mapper 생성
			// LocalDateTime 타입은 json으로 변환할 때 오류가 발생할 수 있기 때문에 registerModule 설정 필요
			// 날짜가 요소별로 분리되어 나오는 기능을 비활성화
			ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
					.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

			// 응답 전송
			PrintWriter out = response.getWriter();
			out.print(objectMapper.writeValueAsString(data));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
