<!-- 클래스 import문 추가 -->
<%@ page import="com.example.demo.domain.MemberRepository" %>
<%@ page import="com.example.demo.domain.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 자바코드를 사용 -->
<%
    MemberRepository repository = new MemberRepository();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Member member = Member.builder()
                            .userId(username).password(password)
                            .build();

        Member newMember = repository.save(member);
%>

<html>

<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>

<body>
    <p>
    	<!-- 자바 객체 출력 -->
        <%= newMember.getNo() %> 번째 <%= newMember.getUserId() %> 회원을 추가했습니다!
    </p>
</body>
</html>