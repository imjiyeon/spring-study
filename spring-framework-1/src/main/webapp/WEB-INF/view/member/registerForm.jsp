<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="/vendor/jquery/jquery.min.js"></script>	
<!DOCTYPE html>
<html lang="en">

<script type="text/javascript">
	$(document).ready(function() {
		var resultMessage = '${resultMessage}';
		if (resultMessage != '') {
			$(".modal-body").html(resultMessage);
			$("#myModal").modal("show");	
		}
	})
</script>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Register</title>

<!-- Custom fonts for this template-->
<link href="/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">회원가입</h1>
							</div>
							<form class="user" action="/register" method="post"> <!-- 사용자등록 request 수정 -->
								<div class="form-group">
									<input type="text" class="form-control form-control-user" name="name" placeholder="이름">
								</div>
								<div class="form-group">
									<input type="text" class="form-control form-control-user" name="id" placeholder="아이디">
								</div>
								<div class="form-group">
									<input type="password" class="form-control form-control-user" name="password" placeholder="패스워드">
								</div>
								<!-- 사용자등급 입력필드 추가 -->
								<div class="form-group">
									<div class="form-check form-check-solid">
									    <input class="form-check-input"  type="radio" id="user" name="role" value="ROLE_USER" checked>
									    <label class="form-check-label" for="user">일반사용자</label>
									</div>
									<div class="form-check form-check-solid">
									    <input class="form-check-input" type="radio" id="admin"name="role" value="ROLE_ADMIN">
									    <label class="form-check-label" for="admin">관리자</label>
									</div>
								</div>			
								<button type="submit" class="btn btn-primary btn-user btn-block">회원가입하기</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- Modal 추가 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">처리결과</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal 끝 -->

	<!-- Bootstrap core JavaScript-->
	<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/js/sb-admin-2.min.js"></script>

</body>

</html>
