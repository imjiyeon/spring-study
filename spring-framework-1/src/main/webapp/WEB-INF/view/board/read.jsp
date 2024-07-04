<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../includes/header.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		//게시물 수정 이벤트 처리
		var modifyResult = '${modifyResult}';
		if (modifyResult != '') {
			if(modifyResult == 'true'){
				$(".modal-body").html("수정을 성공하였습니다");
			}else{
				$(".modal-body").html("수정을 실패하였습니다");
			}
			$("#myModal").modal("show");
		}
	})
</script>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">게시물 상세보기</h1>
	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">상세보기</h6>
		</div>
		<div class="card-body">
				<div class="form-group">
					<label>번호</label> 
					<input class="form-control" name="no" value="${board.no}" readonly />
				</div>
				<div class="form-group">
					<label>제목</label> 
					<input class="form-control" name="title" value="${board.title}" readonly/>
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" name="content" rows="3" readonly>${board.content}</textarea>
				</div>
				<div class="form-group">
					<label>작성자</label>
					<input class="form-control" name="writer" value="${board.writer}" readonly/>
				</div>
				<div class="form-group">
					<label>등록일</label> 
					<input class="form-control" name="regDate" value = "<fmt:formatDate pattern='yyyy/MM/dd hh:mm' value='${board.regDate}'/>"
					 readonly/>
				</div>
				<div class="form-group">
					<label>수정일</label> 
					<input class="form-control" name="updateDate" value = "<fmt:formatDate pattern='yyyy/MM/dd hh:mm' value='${board.updateDate}'/>"
					 readonly/>
				</div>
				<a href="/board/modify?no=${board.no}" class="btn btn-light">수정</a>
				<a href="/board/list" class="btn btn-info">목록</a>				
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
				
<!-- Modal 추가 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">처리결과</h5>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">  </div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>				

<%@include file="../includes/footer.jsp"%>
