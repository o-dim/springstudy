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
<title>LIST IS HERE</title>
<script>
	function fnDetail(n) {
		location.href = "${contextPath}/board/detail.do?board_no=" + n;
	}
</script>
</head>
<body>
	<div>
		 <table border="1">
		 	<thead>
		 		<tr>
		 			<td>제목</td>
		 			<td>글쓴이</td>
		 			<td>날짜</td>
		 		</tr>
		 	</thead>
		 	<tbody>
	 			<c:forEach items="${boardList}" var="b">
			 		<tr onclick="fnDetail(${b.board_no})">
		 				<td>${b.title}</td>
		 				<td>${b.writer}</td>
		 				<td>${b.created_at}</td>
			 		</tr>
	 			</c:forEach>
		 	</tbody>
		 </table>
	</div>
	<div>
		<a href="${contextPath}/board/write.do">새글작성하기</a>
	</div>
</body>
</html>