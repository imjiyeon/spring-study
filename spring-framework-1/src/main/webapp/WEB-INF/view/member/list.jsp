<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../includes/header.jsp"%>


<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">사용자관리</h1>
	<!-- DataTales Example -->
	<div class="card shadow mb-4">

		<div class="card-header py-3">
			<div class="row" style="justify-content: space-between;">
				<h6 class="m-0 font-weight-bold text-primary">사용자</h6>
			</div>
		</div>

		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th>아이디</th>
							<th>이름</th>
							<th>등급</th> <!-- 사용자등급 추가 -->
							<th>등록일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="member" items="${memberList}">
							<tr>
								<td><a href="/member/read?id=${member.id}">${member.id}</a></td>
								<td>${member.name}</td>
								<td>${member.role}</td> <!-- 사용자등급 추가 -->
								<td><fmt:formatDate pattern="yyyy/MM/dd" value="${member.regDate}" /></td>
								<td><fmt:formatDate pattern="yyyy/MM/dd" value="${member.updateDate}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 테이블 끝 -->
			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@include file="../includes/footer.jsp"%>
