<!-- 회원 등록 결과를 보여주는 JSP 파일 -->

<!-- 인코딩 설정 통일 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 이부분은 예제코드에서 복사할 것.. -->
<!-- jstl 라이브러리 추가 (C태그): JSP에서 자바코드를 대체하여 간편하게 작성하는 기능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <p>	
    	<!-- 컨트롤러에서 전달받은 member 객체의 정보를 출력 -->
        <c:out value="${member.no}"/> 번째 <c:out value="${member.userId}"/> 회원을 추가했습니다!
    </p>
</body>
</html>