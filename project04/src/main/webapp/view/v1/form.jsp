<!-- 스프링 부트의 기본 설정이 UTF-8로 되어있어서 euc-kr을 쓰면 한글이 깨지는 현상이 발생함.
따라서 JSP의 모든 인코딩을 UTF-8로 설정 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<!-- 인코딩을 UTF-8로 통일 -->
<meta charset="UTF-8">
<title>회원 등록</title>
</head>
<body>
	<!-- 회원 등록 폼 생성 -->
    <form action="/view/v1/save.jsp" method="post">
        이름: <input type="text" name="username" />
        암호: <input type="text" name="password" />
        <button type="submit">전송</button>
    </form>
</body>
</html>
