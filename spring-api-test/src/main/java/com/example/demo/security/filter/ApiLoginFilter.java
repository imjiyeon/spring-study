package com.example.demo.security.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.demo.dto.MemberDTO;
import com.example.demo.security.dto.CustomUser;
import com.example.demo.security.util.JWTUtil;
import com.example.demo.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/*
 * 로그인 요청이 들어오면 아이디와 패스워드를 확인하고 jwt토큰을 발급하는 필터
 * */

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

	private JWTUtil jwtUtil;

	private MemberService memberService;

	public ApiLoginFilter(String defaultFilterProcessesUrl, JWTUtil jwtUtil, MemberService memberService) {

		super(defaultFilterProcessesUrl);
		this.jwtUtil = jwtUtil;
		this.memberService = memberService;
	}

	// 로그인 요청이 들어오면 아이디와 패스워드를 확인해서 인증하기
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		//바디에서 사용자 정보 꺼내기
		String body = getBody(request);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> map = objectMapper.readValue(body, HashMap.class);
		
		String id = map.get("id");
		String pw = map.get("pw");

		if (id == null) {
			throw new BadCredentialsException("id cannot be null");
		}

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(id, pw);

		return getAuthenticationManager().authenticate(authToken);
	}

	// 인증에 성공했으면 jwt토큰을 발급하고 응답하기
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		log.info("-----------------ApiLoginFilter---------------------");
		log.info("successfulAuthentication: " + authResult);

		log.info(authResult.getPrincipal());

		String id = ((CustomUser) authResult.getPrincipal()).getUsername();

		String token = null;
		try {
			// 토큰 발급
			token = jwtUtil.generateToken(id);
			log.info(token);

			// 사용자 정보 꺼내기
			MemberDTO member = memberService.read(id);

			// 결과 데이터 만들기
			HashMap<String, Object> data = new HashMap<>();
			// 에러났던 부분.. 이전에는 토큰을 바이너리(바이트배열)로 반환했음
			// 지금은 json문자열로 변환하기 때문에, 토큰을 그대로 담아야함
			data.put("token", token);
			data.put("user", member);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			// 객체 -> json문자열 변환
			ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
					.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
					.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

			PrintWriter out = response.getWriter();
			out.print(objectMapper.writeValueAsString(data));

		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
