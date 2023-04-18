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
<title>jot that down</title>
<script>
	function fnList() {
		location.href = "${contextPath}/board/list.do";
	}
</script>
</head>
<body>
	<h1>~ ${b.board_no}번째 게시물~</h1>
	<form action="${contextPath}/board/add.do" method="post">
		<div>
			<label for="title">제목</label>
			<input type="text" name="title" id="title">
		</div>
		<div>
			<label for="writer">작성자</label>
			<input type="text" name="writer" id="writer">
		</div>
		<div>
			<div><label for="contnet">내용을 입력하세요</label></div>
			<textarea name="content" id="content"></textarea>
		</div>
		<div>
			<button>입력완료</button>
			<input type="button" value="뒤로가기" onclick="fnList()">
		</div>
	</form>
</body>
</html>