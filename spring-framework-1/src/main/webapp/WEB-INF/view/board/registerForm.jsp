<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../includes/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">게시물 등록하기</h1>
	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">등록폼</h6>
		</div>

		<div class="card-body">
			<form role="form" action="/board/register" method="post">
				<div class="form-group">
					<label>제목</label> 
					<input class="form-control" name="title" placeholder="제목을입력하세요" />
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" name="content" rows="3" placeholder="제목을입력하세요"></textarea>
				</div>
				<div class="form-group">
					<label>작성자</label>
					<input class="form-control" name="writer" placeholder="작성자를입력하세요" />
				</div>
				<button type="submit" class="btn btn-light">등록</button>
				<button type="reset" class="btn btn-light">리셋</button>
			</form>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@include file="../includes/footer.jsp"%>
