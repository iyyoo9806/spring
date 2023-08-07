<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>회원 게시판</title>

    <!-- Custom fonts for this template -->
    <link href="resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Wise</div>
            </a>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Pages</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Login Screens:</h6>
                        <a class="collapse-item" href="logout">Logout</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Tables -->
            <li class="nav-item active">
                <a class="nav-link" href="board?num=1">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Tables</span></a>
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
                    <!-- DataTales Example 여기부터-->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3 d-flex justify-content-between align-items-center">
            			<h6 class="m-0 font-weight-bold text-primary">${sessionScope.name}님의 게시물
            			<a href="logout" class="btn btn-primary">로그아웃</a></h6>
				            <!-- <a href="#" class="btn btn-secondary btn-icon-split"> -->
				                <a href="write" class="btn btn-primary">작성</a>
				            <!-- </a> -->
				        </div>
						<!-- 여기까지 -->
                        <div class="card-body">
                            <div class="table-responsive">
                            <!-- 여기서 부터 테이블? -->
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>제목</th>
                                            <th>이름</th>
                                            <th>직급</th>
                                            <th>작성일</th>
                                        </tr>
                                    </thead>
                                   
                                    <tbody>
                                    	<c:forEach var="boardList" items="${boardList }" varStatus="status">
                                        <tr>
                                            <td><a href="/view?idx=${boardList.idx}">${boardList.title}</a></td>
                                            <td>${boardList.name }</td>
                                            <td>${boardList.degree }</td>
                                            <fmt:parseDate  value="${boardList.date }" pattern="yyyyMMdd" var="parsedDate" />
                                            <td><fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" /></td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                
                                <div align="center">
                                <c:if test="${page.prev}">
								 <span>[ <a href="/board?num=${page.startPageNum - 1}${page.searchTypeKeyword}">이전</a> ]</span>
								</c:if>
								
								<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
								 <span>
								 
								  <c:if test="${select != num}">
								   <a href="/board?num=${num}${page.searchTypeKeyword}">${num}</a>
								  </c:if>    
								  
								  <c:if test="${select == num}">
								   <b>${num}</b>
								  </c:if>
								  
								 </span>
								</c:forEach>
								
								<c:if test="${page.next}">
								 <span>[ <a href="/board?num=${page.endPageNum + 1}${page.searchTypeKeyword}">다음</a> ]</span>
								</c:if>
								
								<div>
								  <select name="searchType">
								      <option value="title"  <c:if test="${page.searchType eq 'title'}">selected</c:if>>제목</option>
								      <option value="date" <c:if test="${page.searchType eq 'date'}">selected</c:if>>작성일</option>
								  </select>
								  
								  <input type="text" name="keyword" value="${page.keyword }"/>
								  
								  <button type="button" class="btn btn-primary" id="searchBtn">검색</button>
								</div>
								</div>
                            
                            </div>
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

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script src="resources/vendor/jquery/jquery.min.js"></script>
    <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="resources/js/sb-admin-2.min.js"></script>
    
        <script type="text/javaScript" language="javascript" defer="defer">
    
    	document.getElementById("searchBtn").onclick = function () {
        
    	  let searchType = document.getElementsByName("searchType")[0].value;
    	  let keyword =  document.getElementsByName("keyword")[0].value;
    	  
    	  location.href = "/board?num=1" + "&searchType=" + searchType + "&keyword=" + keyword;
    	 };
    </script>

</body>

</html>