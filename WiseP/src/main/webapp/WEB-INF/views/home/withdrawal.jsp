<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page
	import="org.springframework.security.core.userdetails.UserDetails"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<!-- <script src='https://code.jquery.com/jquery-3.3.1.min.js'></script> -->
<title>회원탈퇴</title>

<!-- Custom fonts for this template-->
<link href="resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function() {

		$("#withdrawalBtn").click(function() {
			if ($("#id").val() === "회원 탈퇴") {
				if (!confirm("정말 회원탈퇴 하시겠습니까?")) {
					alert("취소하였습니다.")
				} else {
					let data = {
						hiddenId : $("#hiddenId").val()
					}
					
					$.ajax({
						type : "PUT",
						url : "/api/withdrawal",
						data : JSON.stringify(data),
						contentType : "application/json",
						success : function(response) {
							if(response.status === "success"){
								alert(response.message)
								location.href = "/";
							}else{
								alert(response.message);
							}
						},
						error : function(xhr, status, error) {
							console.error(xhr.responseText);
						}
					})
				}

			} else {
				alert("잘못된 입력");
			}
		})
	})
</script>

</head>
<c:set var="userDetails"
	value="<%=SecurityContextHolder.getContext().getAuthentication().getPrincipal()%>" />
<input type="hidden" id="principalInput" name="principalInput"
	value="${userDetails}" />
<body class="bg-gradient-primary">

	<div class="container">
		<sec:authentication property="principal" var="principal" />
		<input type="hidden" id= "hiddenId" value = "${principal}">


		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">탈퇴를 원하시면</h1>
										<h1 class="h4 text-gray-900 mb-4">회원 탈퇴를 입력하세요</h1>
									</div>

									<div class="form-group">
										<input type="text" class="form-control" id="id" name="id"
											required="required">
									</div>
									<button id="withdrawalBtn"
										class="btn btn-primary btn-user btn-block">회원 탈퇴</button>

									<hr>
									<div class="text-center">
										<a class="small" href="/board">뒤로가기</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<c:if test="${not empty message}">
		<script>
			alert('<c:out value="${message}" />');
		</script>
	</c:if>

	<!-- Bootstrap core JavaScript-->
	<script src="resources/vendor/jquery/jquery.min.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="resources/js/sb-admin-2.min.js"></script>

</body>
</html>