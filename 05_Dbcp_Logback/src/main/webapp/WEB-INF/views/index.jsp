<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/summernote-0/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0/summernote-lite.min.css">
<style>
	body {
		background-color: black;
	}
	body > h1 {
		color: white;
	}
	body > h1 > a {
		color : white;
	}
	
</style>
<title>WELCOME!</title>
</head>
<body>
	<h1><MARQUEE  scrollamount="30">~~~화녕함니다@환영함니당e~~~</MARQUEE></h1>
	<h1><a href="${contextPath}/board/list.do"></a></h1>
</body>
</html>