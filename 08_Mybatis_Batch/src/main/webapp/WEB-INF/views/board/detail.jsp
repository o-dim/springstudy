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
<title>detail</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/summernote-0/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0/summernote-lite.min.css">
</head>
<script>
	function fnList() {
		location.href = "${contextPath}/board/list.do";
	}
	function fnRemove() {
		if(confirm('삭제하시겠습니까?')){
			$('#frm_remove').submit();
		}
	}
	function fnEdit() {
		$('#edit_screen').show();
		$('#detail_screen').hide();
	}
	function fnBack(){
		$('#edit_screen').hide();
		$('#detail_screen').show();
	}
	$(function(){
		$('#content').summernote({
			width: 640,
			height: 480,
			lang: 'ko-KR',
			toolbar: [
				['style', ['bold', 'italic', 'underline', 'clear']],
				['font', ['strikethrough', 'superscript', 'subscript']],
				['fontname', ['fontname']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['table', ['table']],
				['insert', ['link', 'picture', 'video']],
				['view', ['fullscreen', 'codeview', 'help']]
			]
		})
		$('#edit_screen').hide();  // 최초 편집화면은 숨김
	})
</script>
<body>
	<div id="detail_screen">
		<h1>${b.boardNo} : 상세보기</h1>
		<h2>${b.title}</h2>
		<div>
			<span>${b.writer}</span>&nbsp;&nbsp;&nbsp;<span>${b.createdAt}</span>&nbsp;&nbsp;<span>${b.modifiedAt}</span>
		</div>
		<div>${b.content}</div>
		<form action="${contextPath}/board/remove.do" id="frm_remove" method="post">
			<input type="hidden" name="boardNo" value="${b.boardNo}">
		</form>
		<div>
			<input type="button" value="편집" onclick="fnEdit()">
			<input type="button" value="삭제" onclick="fnRemove()">
			<input type="button" value="목록" onclick="fnList()">
		</div>
	</div>
	<div id="edit_screen">
		<div style="cursor: pointer;" onclick="fnBack()"><i class="fa-solid fa-arrow-left"></i> 뒤로 가기</div>
		<h1>${b.boardNo} : 수정하기</h1>
		<form action="${contextPath}/board/modify.do" method="post">
			<div>
				<label for="title">title</label>
				<input type="text" id="title" name="title" value="${b.title}"> 
			</div>
			<div>
				<label for="content">content</label>
				<textarea id="content" name="content">${b.content}</textarea>
			</div>
			<div>
				<input type="hidden" name="boardNo" value="${b.boardNo}">
				<button>done!</button>
				<input type="button" value="toLIST" onclick="fnList()">
			</div>
		</form>
	</div>
</body>
</html>