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
	// 사용자 요청을 처리하는 메소드로, 사용자 요청이 오면 request와 response 객체가 자동으로 생성됨
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 응답 메세지 설정 (컨텐츠 타입과 문자 인코딩)
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		// 응답 데이터를 작성하기 위해 wrtier 객체 생성
		PrintWriter w = response.getWriter();
		 
		// 회원 정보를 입력할 수 있는 html form 태그를 만들어서 응답에 추가
		// 자바코드로 html을 작성해야 해서 힘들다.. 이부분은 실습코드에서 복사할 것!
		w.write("<!DOCTYPE html>\n" +
		"<html>\n" +
		"<head>\n" +
		" <meta charset=\"UTF-8\">\n" +
		" <title>회원 등록</title>\n" +
		"</head>\n" +
		"<body>\n" +
		"<form action=\"/servlet/save\" method=\"post\">\n" + // 폼을 전송할 주소
		" 이름: <input type=\"text\" name=\"username\" />\n" + // 이름 입력 필드
		" 암호: <input type=\"text\" name=\"password\" />\n" + // 패스워드 입력 필드
		" <button type=\"submit\">전송</button>\n" + // 전송 버튼
		"</form>\n" +
		"</body>\n" +
		"</html>\n");
	}
	
	// 이제 브라우저에서 'localhost:8080/servlet/form'을 호출하면 회원 등록 폼이 나타난다

	// 회원 등록 폼에 회원 정보를 입력하고 "전송" 버튼을 클릭하면, 데이터가 전송되고 저장 결과를 확인할 수 있다.
	// 하지만, 데이터를 전송해도 처리할 서블릿이 없다. 새로운 회원을 추가할 서블릿을 구현해야 한다.
}
