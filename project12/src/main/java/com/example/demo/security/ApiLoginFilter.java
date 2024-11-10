package com.example.demo.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

	// 나중에 접근제어자 변경
//	protected ApiLoginFilter(String defaultFilterProcessesUrl) {
	public ApiLoginFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		String body = getBody(request);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> map = objectMapper.readValue(body, HashMap.class);

		String id = map.get("id");
		String password = map.get("password");

		if (id == null) {
			throw new BadCredentialsException("id cannot be null");
		}

		return null;
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
