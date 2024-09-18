<!-- 인코딩을 UTF-8로 통일 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- HTML에서 자바코드는 자동완성이 안되서 작성하기가 힘들다.. 이부분은 실습코드에서 복사할 것! -->
<!-- 필요한 클래스 import -->
<%@ page import="com.example.demo.domain.MemberRepository" %>
<%@ page import="com.example.demo.domain.Member" %>

<!-- 자바 코드를 사용하여 새로운 회원을 저장 -->
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
<!-- 인코딩을 UTF-8로 통일 -->
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
    <p>
    	<!-- 자바 코드를 사용하여 저장 결과 출력 -->
    	<!-- 위에서 저장된 회원 정보를 출력  -->
        <%= newMember.getNo() %> 번째 <%= newMember.getUserId() %> 회원을 추가했습니다!
    </p>
</body>
</html>