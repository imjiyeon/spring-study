<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../includes/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">게시물 수정하기</h1>
	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">수정폼</h6>
		</div>

		<div class="card-body">
			<form role="form" action="/board/modify" method="post">
				<div class="form-group">
					<label>번호</label> 
					<input class="form-control" name="no" value="${board.no}" readonly />
				</div>
				<div class="form-group">
					<label>제목</label> 
					<input class="form-control" name="title" value="${board.title}" />
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" name="content" rows="3">${board.content}</textarea>
				</div>
				<div class="form-group">
					<label>작성자</label> 
					<input class="form-control" name="writer" value="${board.writer}"/>
				</div>

				<div class="form-group">
					<label>작성일</label> 
					<input class="form-control" name="regDate" value = "<fmt:formatDate pattern='yyyy/MM/dd hh:mm' value='${board.regDate}'/>"
						readonly />
				</div>
				<div class="form-group">
					<label>수정일</label> 
					<input class="form-control" name="updateDate" value = "<fmt:formatDate pattern='yyyy/MM/dd hh:mm' value='${board.updateDate}'/>" 
						readonly />
				</div>
				<button type="submit" class="btn btn-light">수정</button>
				<a href="/board/remove?no=${board.no}" class="btn btn-danger">삭제</a>
				<a href="/board/list" class="btn btn-info">목록</a>				
			</form>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@include file="../includes/footer.jsp"%>
