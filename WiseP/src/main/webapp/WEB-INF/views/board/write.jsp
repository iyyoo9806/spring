<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>게시글 작성</title>

<!-- Custom fonts for this template -->
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	type="text/css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet"
	type="text/css">

<!-- Custom styles for this page -->
<link href="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.css"
	type="text/css" rel="stylesheet">

<script
	src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js""></script>

</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">
					Wise<sup> Itech</sup>
				</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item"><a class="nav-link" href="/logout"> <span>로그아웃</span></a>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">카테고리</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseOne"
				aria-expanded="true" aria-controls="collapseOne"> <span>회원탈퇴</span>
			</a>
				<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="/withdrawal">탈퇴</a>
					</div>
				</div></li>
			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <span>게시판</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">목록</h6>
						<a class="collapse-item" href="/board">일반게시판</a> <a
							class="collapse-item" href="/a">미정</a>
					</div>
				</div></li>
			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseUtilities"
				aria-expanded="true" aria-controls="collapseUtilities"> <span>분석</span>
			</a>
				<div id="collapseUtilities" class="collapse"
					aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">목록</h6>
						<a class="collapse-item" href="/board/analytic">자동차 분석</a> <a
							class="collapse-item" href="/a">분석2</a>
					</div>
				</div></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<sec:authentication property="principal" var="principal" />
					<h1 class="h3 mb-2 text-gray-800">
						${principal}님 환영합니다.
						<input type="hidden" id= "hiddenId" value = "${principal}">
					</h1>
					<div class="panel-body">
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">제목</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="title" name="title"
									placeholder="제목을 입력하세요" maxLength="100" required="required">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">내용</label>
							<div class="col-sm-10">
								<textarea class="form-control" rows="5" id="contentText"
									name="contents" placeholder="내용을 입력하세요" maxlength="1000"
									required="required"></textarea>
								<script>
									var ckeditor_config = {
										resize_enabled : false,
										enterMode : CKEDITOR.ENTER_BR,
										shiftEnterMode : CKEDITOR.ENTER_P,
									};

									CKEDITOR.replace("contentText",
											ckeditor_config);
								</script>
							</div>
						</div>
						<div class="panel-footer">
							<button class="btn btn-default" id="writeBtn">작성</button>
							<button type="button" class="btn btn-default" onclick="cancel();">취소</button>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->


	<!-- Bootstrap core JavaScript-->
	<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javaScript" language="javascript" defer="defer">
		$(document).ready(function() {
			$("#writeBtn").click(function() {
				let editor = CKEDITOR.instances.contentText;
				let data = {
					title : $("#title").val(),
					contents : editor.getData(),
					hiddenId : $("#hiddenId").val()
				};

				if (data.title === "") {
					alert("제목을 입력하세요");
					return false;
				}

				if (data.contents === "") {
					alert("내용을 입력하세요")
					return false;
				}

				$.ajax({
					type : 'POST',
					url : '/api/write',
					data : JSON.stringify(data),
					contentType : "application/json",
					success : function(response) {
						if (response.status === 'success') {
							alert(response.message);
							window.location.href = "/board";
						} else {
							alert(response.message);
						}
					},
					error : function(xhr, status, error) {
						console.error(xhr.responseText);
					}
				})
				return true;
			})
		})

		function cancel() {
			location.href = "<c:url value='/board'/>";
		}
	</script>

</body>

</html>