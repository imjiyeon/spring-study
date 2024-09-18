package com.example.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 회원 등록 폼을 전송할 서블릿 만들기

// HttpServlet 클래스를 상속받고, 서블릿 이름과 URL 설정
@WebServlet(name = "FormServlet", urlPatterns = "/servlet/form")
public class FormServlet extends HttpServlet { 

	// HttpServlet에게 물려받은 service 메소드 재정의하기
	// 사용자 요청을 처리하는 메소드로, 사용자 요청이 오면 요청 및 응답 객체가 자동으로 생성됨
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 응답 메세지 만들기
		// 컨텐츠 타입과 인코딩 설정
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		// 응답 데이터를 작성하기 위해 wrtier 객체 생성
		PrintWriter w = response.getWriter();
		 
		// 회원 정보를 입력하기 위해 html form 태그를 작성하여 응답데이터에 추가
		// 이부분은 실습코드에서 복사할 것!
		w.write("<!DOCTYPE html>\n" +
		"<html>\n" +
		"<head>\n" +
		" <meta charset=\"UTF-8\">\n" +
		" <title>Title</title>\n" +
		"</head>\n" +
		"<body>\n" +
		"<form action=\"/servlet/save\" method=\"post\">\n" +
		" 이름: <input type=\"text\" name=\"username\" />\n" +
		" 암호: <input type=\"text\" name=\"password\" />\n" +
		" <button type=\"submit\">전송</button>\n" +
		"</form>\n" +
		"</body>\n" +
		"</html>\n");

	}

}
