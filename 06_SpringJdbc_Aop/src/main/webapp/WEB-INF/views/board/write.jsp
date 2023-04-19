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
<title>write</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/summernote-0/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0/summernote-lite.min.css">
<script type="text/javascript">
	$(function() {
		$('#content').summernote({
			width: 640,
			height: 480,
			lang: 'ko-KR',
			toolbar: [
			   ['style', ['bold', 'italic', 'underline', 'clear']],
			   ['font', ['bold', 'underline', 'clear']],
		 	   ['para', ['ul', 'ol', 'paragraph']],
			   ['table', ['table']],
			   ['insert', ['link', 'picture', 'video']],
			   ['view', ['fullscreen', 'codeview', 'help']]
		    ]
		})
	})
	function fnList() {
		location.href = "${contextPath}/board/list.do";
	}
</script>
</head>
<body>
	<div>
		<h1>write</h1>
	</div>
	<div>
		<form action="${contextPath}/board/add.do" method="post">
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title">
			</div>
			<div>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer">
			</div>
			<div>
				<div><label for="content">내용</label></div>
				<textarea id="content" name="content"></textarea>
			</div>
			<div>
				<button>done!</button>
				<input type="button" value="toLIST" onclick="fnList()"> 
			</div>
		</form>
	</div>
</body>
</html>