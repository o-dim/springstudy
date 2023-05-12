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
<script>
	$(function() {
		fnList(); // 본문 읽은 다음에 호출할 수 있도록 만들기
	})
	
	function fnList() {
		$.ajax({
			type : 'get',
			url : '${contextPath}/list.json',
			dataType : 'json',
			success : function(resData){ // resData = [{}, {}, {}]
				$('#staffList').empty();
				$.each(resData, function(i, staff) {
					let str = '<tr>';
					str += '<td>' + staff.sno;
					str += '<td>' + staff.name;
					str += '<td>' + staff.dept;
					str += '<td>' + staff.salary;
					$('#staffList').append(str);
				})
			}
		})
		
	}
	function fnAdd() {
		var snoTest = /[0-9]{5}/;
		if(snoTest.test($('#sno').val()) == false){
			alert('사원번호는 5자리 숫자입니다.');
		}
		var depTest = /[가-힣]{3,5}/;
		if(depTest.test($('#dept').val()) == false) {
			alert('부서는 3~5자리 한글입니다');
		}
		$.ajax({
			type : 'post',
			url : '${contextPath}/add.do',
			data : $('#frm_add').serialize(),
			dataType : 'text',
			success : function(resData) {
				alert(resData);
				fnList();
				$('#sno').val('');
				$('#name').val('');
				$('#dept').val('');
			},
			error : function(jqXHR) {
				alert(jqXHR.responseText);
			}
		})
	}
	
	function fnSearch() {
		$.ajax({
			type : 'get',
			url : '${contextPath}/query.json?query=' + $('#query').val(),
			dataType : 'json',
			success : function(resData) {
				$('#staffList').empty();
				let str = '<tr>';
				str += '<td>' + resData.sno;
				str += '<td>' + resData.name;
				str += '<td>' + resData.dept;
				str += '<td>' + resData.salary;
				$('#staffList').append(str);
			},
			error : function(jqXHR) {
				alert('조회된 사원이 없습니다');
				fnList();
			}
		})
	}
	
</script>
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