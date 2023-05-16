<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>

</head>
<body>
	<div>
		<a href="${contextPath}/user/agree.form">회원가입</a>
		<a href="${contextPath}/user/login.form">로그인</a>
	</div>
</body>
</html>