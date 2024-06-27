<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl 라이브러리 추가 (C태그) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
    <p>	
    	<!-- jstl: jsp 페이지에서 java 코드를 쉽게 작성할 수 있는 태그 라이브러리 -->
    	<!-- 컨트롤러에서 전달받은 member 객체의 정보를 출력 -->
        <c:out value="${member.no}"/> 번째 <c:out value="${member.userId}"/> 회원을 추가했습니다!
    </p>
</body>
</html>