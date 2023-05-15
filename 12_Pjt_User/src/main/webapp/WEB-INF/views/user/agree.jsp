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
		<h3>사원등록</h3>
		<div>
			<form id="frm_add">
				<input type="text" id="sno" name="sno" placeholder="사원번호">
				<input type="text" id="name" name="name" placeholder="사원명">
				<input type="text" id="dept" name="dept" placeholder="부서명">
				<input type="button" value="등록" onclick="fnAdd()">						
			</form>
		</div>
	</div>
	
	<hr>
	
	<div>
		<h3>사원조회</h3>
		<form id="frm_search">
			<input type="text" id="query" name="query" placeholder="사원번호입력">
			<input type="button" value="조회" onclick="fnSearch()">
			<input type="button" value="전체" onclick="fnList()">
		</form>
	</div>
	
	<hr>
	
	<div>
		<h3>사원목록</h3>
		<table border="1">
			<thead>
				<tr>
					<td>사원번호</td>
					<td>사원명</td>
					<td>부서명</td>
					<td>연봉</td>
				</tr>
			</thead>
			<tbody id="staffList">
			</tbody>
		</table>
	</div>
</body>
</html>