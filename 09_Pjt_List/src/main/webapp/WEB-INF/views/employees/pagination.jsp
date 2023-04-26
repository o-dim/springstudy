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
	$(function(){
		// recordPerPage의 변경
		$('#recordPerPage').on('change', function(){
			location.href = '${contextPath}/employees/change/record.do?recordPerPage=' + $(this).val();  // session에 recordPerPage 올리기
		})
		// 세션에 저장된 recordPerPage값으로 <select> 태그의 값을 세팅
		let recordPerPage = '${sessionScope.recordPerPage}' == '' ? '10' : '${sessionScope.recordPerPage}';
		$('#recordPerPage').val(recordPerPage);
	})
</script>
<style>
	.hidden {
		visibility : hidden;
	}
	.strong {
		font-weight: 900;
	}
	.link {
	}
</style>
<title>THIS IS INDEX</title>
</head>
<body>
	<div>
		<MARQUEE  scrollamount="10">
		<a href="${contextPath}/employees/search.form">사원 조회 화면으로 이동하기!!!!!!!</a>
		</MARQUEE>
	</div>
	
	<div>
		<h1>사원 목록</h1>
		<div>
			<select id="recordPerPage">
				<option value="10">10개</option>
				<option value="20">20개</option>
				<option value="30">30개</option>
			</select>
		</div>
		<hr>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>사원번호</td>
					<td>사원명</td>
					<td>이메일</td>
					<td>전화번호</td>
					<td>입사일자</td>
					<td>직업</td>
					<td>연봉</td>
					<td>커미션</td>
					<td>매니저</td>
					<td>부서번호</td>
					<td>부서명</td>
				</tr>	
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="emp" varStatus="vs">
				<tr>
					<td>${vs.index}</td>
					<td>${emp.employeeId}</td>
					<td>${emp.firstName} ${emp.lastName}</td>
					<td>${emp.email}</td>
					<td>${emp.phoneNumber}</td>
					<td>${emp.hireDate}</td>
					<td>${emp.jobId}</td>
					<td>${emp.salary}</td>
					<td>${emp.commissionPct}</td>
					<td>${emp.managerId}</td>
					<td>${emp.deptDTO.departmentId}</td>
					<td>${emp.deptDTO.departmentName}</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="12">
						<div>${pagination}</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>