package com.example.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/*
 * 로그인시 발생하는 에러를 핸들링하는 클래스이다.
 * 사용자가 존재하지않거나 비밀번호가 틀릴경우 에러를 처리한다.
 * */

public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String loginFailMessage;
		if (exception instanceof BadCredentialsException) { //비밀번호가 일치하지않을때
			loginFailMessage = "비밀번호가 일치하지 않습니다.";  //에러메세지 작성
		} else if (exception instanceof UsernameNotFoundException) { //아이디가 존재하지않을때
			loginFailMessage = "계정을 찾을 수 없습니다.";
		} else {
			loginFailMessage = "시스템 오류로 로그인에 실패했습니다.";
		}
		response.setContentType("text/html; charset=UTF-8"); //파라미터 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("/customLoginFail?loginFailMessage="+loginFailMessage).forward(request,response); //에러메세지 전달
		// 로그인 실패 처리 reqeust를 호출한다
	}

}