package com.example.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 회원 등록 폼에서 입력된 정보를 받아 새로운 회원을 저장하는 서블릿 만들기

// HttpServlet 클래스를 상속받고, 서블릿 이름과 URL 설정
@WebServlet(name = "SaveServlet", urlPatterns = "/servlet/save")
public class SaveServlet extends HttpServlet {

	// 회원정보를 관리할 리파지토리 생성 
	MemberRepository repository = new MemberRepository();

	// service 메소드를 재정의하여 사용자 요청을 처리
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        // 요청 메세지에서 사용자가 전달한 파라미터를 조회
		// 회원 이름과 패스워드를 꺼내서 Member 객체를 생성
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Member member = new Member(0, username, password);
        
        // 생성된 회원 객체를 저장소에 등록
        repository.save(member);

		// 응답 메세지 설정 (컨텐츠 타입과 문자 인코딩)
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        
        PrintWriter w = response.getWriter();
        
        // 등록 결과를 확인하기 위한 html을 만들어서 응답에 추가
        // 자바코드로 html을 작성해야 해서 힘들다.. 이부분도 실습코드에서 복사할 것!
        w.write("<html>\n" +
        "<head>\n" +
        " <meta charset=\"UTF-8\">\n" +
        "</head>\n" +
        "<body>\n" +
        "성공\n" +
        "<ul>\n" +
        " <li>회원번호="+member.getNo()+"</li>\n" +
        " <li>이름="+member.getUserId()+"</li>\n" +
        " <li>비밀번호="+member.getPassword()+"</li>\n" +
        "</ul>\n" +
        "</body>\n" +
        "</html>");
	}

}
