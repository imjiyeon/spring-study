<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../includes/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">사용자 상세보기</h1>
	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">상세보기</h6>
		</div>
		<div class="card-body">
				<div class="form-group">
					<label>이름</label> 
					<input class="form-control" name="name" value="${member.name}" readonly/>
				</div>
				<div class="form-group">
					<label>아이디</label> 
					<input class="form-control" name="id" value="${member.id}" readonly />
				</div>
				<div class="form-group">
					<label>패스워드</label> 
					<input type="password" class="form-control" name="password" value="${member.password}" readonly/>
				</div>
				<div class="form-group"> <!-- 사용자등급 추가 -->
					<label>등급</label> 
					<input class="form-control" name="role" value="${member.role}" readonly />
				</div>
				<div class="form-group">
					<label>등록일</label> 
					<input class="form-control" name="regDate" value = "<fmt:formatDate pattern='yyyy/MM/dd hh:mm' value='${member.regDate}'/>" readonly/>
				</div>
				<div class="form-group">
					<label>수정일</label> 
					<input class="form-control" name="updateDate" value = "<fmt:formatDate pattern='yyyy/MM/dd hh:mm' value='${member.updateDate}'/>" readonly/>
				</div>
				<a href="/member/list" class="btn btn-info">목록</a>
				
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@include file="../includes/footer.jsp"%>
