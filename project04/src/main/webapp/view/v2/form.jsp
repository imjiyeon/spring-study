<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!-- 상대경로 -->
    <form action="save" method="post"> 
        이름: <input type="text" name="username" />
        암호: <input type="text" name="password" />
        <button type="submit">전송</button>
    </form>
</body>
</html>