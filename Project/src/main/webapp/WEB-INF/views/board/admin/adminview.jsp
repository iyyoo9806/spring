<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<link href="resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="resources/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
	
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
	
<script type="text/javaScript" language="javascript" defer="defer">
function cancel(){
	location.href = "<c:url value='/board?num=1'/>";
}
</script>
	
</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a class="sidebar-brand d-flex align-items-center justify-content-center">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">Wise</div>
			</a>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-folder"></i> <span>Pages</span>
			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Login Screens:</h6>
						<a class="collapse-item" href="logout">Logout</a>
					</div>
				</div></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item active"><a class="nav-link"
				href="board?num=1"> <i class="fas fa-fw fa-table"></i> <span>Tables</span></a>
			</li>

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

					<div class="panel-body">
						<form class="form-horizontal" method="post" action="modifying">
							<div class="form-group">
								<label class="control-label col-sm-2" for="pwd">제목</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="title" name="title"
										value="${read.title }" maxLength="100" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="pwd">내용</label>
								<div class="col-sm-10">
									<textarea class="form-control" rows="5" id="contentText" name="contents"
										maxlength="1000" readonly="readonly">${read.contents }</textarea>
										
									<script>
									 var ckeditor_config = {
									   resize_enabled : false,
									   enterMode : CKEDITOR.ENTER_BR,
									   shiftEnterMode : CKEDITOR.ENTER_P,
									 };
									 
									 CKEDITOR.replace("contentText", ckeditor_config);
									</script>			
									
										<div class="file-info">
							    			<span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
							    			<br>
							    			첨부파일
							    			<br>
							    			<c:forEach items="${fileNames}" var="filename">
								    			<a href='<c:url value="/download?idx=${read.idx}&filename=${filename}" />'><c:out value="${filename}" /></a><br>
											</c:forEach>
										</div>							
										
								</div>
							</div>
							<div class="panel-footer">
								<button type="button" class="btn btn-default" onclick="cancel();">취소</button>
							</div>
						</form>
					</div>

				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Bootstrap core JavaScript-->
	<script src="resources/vendor/jquery/jquery.min.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="resources/js/sb-admin-2.min.js"></script>

	
</body>

</html>