<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<script>
	alert('${message}');
    location.href='<c:out value="${pageContext.request.contextPath}"/>${url}';
</script>
<head>
    <title>알림창</title>
</head>
<body>
</body>
</html>