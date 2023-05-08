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
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	function fnList() {
		location.href = '${contextPath}/bbs/list.do';
	}
</script>
<title>THIS IS INDEX</title>
</head>
<body>
	<div>
		<h2>BBS작성</h2>
		<form action="${contextPath}/bbs/add.do" method="post">
			<div>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer" required="required">
			</div>
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" required="required">
			</div>
			<div>
				<button>작성완료</button>
				<input type="reset" value="입력초기화">
				<input type="button" value="목록" onclick="fnList()">
			</div>
		</form>
	</div>
</body>
</html>