<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="includes/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- <h1> <sec:authentication property="principal"/> </h1> <!-- 인증객체 확인 -->
	<h1 class="h3 mb-4 text-gray-800">안녕하세요 <sec:authentication property="principal.Username"/> 님 </h1>

</div>
</div>

<%@include file="includes/footer.jsp"%>


