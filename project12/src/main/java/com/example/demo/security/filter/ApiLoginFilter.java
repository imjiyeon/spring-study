package com.example.demo.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.demo.security.dto.CustomUser;
import com.example.demo.security.util.JWTUtil;

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

    public ApiLoginFilter(String defaultFilterProcessesUrl, JWTUtil jwtUtil) {

        super(defaultFilterProcessesUrl);
        this.jwtUtil = jwtUtil;
    }

    // 로그인 요청이 들어오면 아이디와 패스워드를 확인해서 인증하기
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id == null) {
			throw new BadCredentialsException("id cannot be null");
		}
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(id, pw);

		return getAuthenticationManager().authenticate(authToken);
	}

	// 인증에 성공했으면 jwt토큰을 발급하고 응답하기
	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        log.info("-----------------ApiLoginFilter---------------------");
        log.info("successfulAuthentication: " + authResult);

        log.info(authResult.getPrincipal());

        //email address
        String email = ((CustomUser)authResult.getPrincipal()).getUsername();

        String token = null;
        try {
            token = jwtUtil.generateToken(email);

            response.setContentType("text/plain");
            response.getOutputStream().write(token.getBytes());

            log.info(token);
            
         // JWT 토큰 생성 및 응답 헤더에 추가
            response.setHeader("Authorization", "Bearer " + token);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}





