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
		$('#query').on('keyup', function() {
			$('#auto_complete').empty();
			$.ajax({
				type : 'get',
				url : '${contextPath}/employees/autoComplete.do',
				data : $('frm1').serialize(),
				dataType : 'json',
				success : function(resData) {
					let property = '';
					switch (@('#column').val()) {
					case "E.FIRST_NAME" : property = 'firstName';					
						break;
					case "E.PHONE_NUMBER" : property = 'phoneNumber';					
						break;
					case "D.DEPARTMENT_NAME" : property = 'departmentName';					
						break;
					}
					$.each(resData.employees, function(i, employee) {
						let str = '<option value"' + employee[property] + '" />';
						$('#auto_complete').append(str);
					})
				}
				
			})
		})
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
	table {
		width: 1100px;
	}
</style>
<title>THIS IS INDEX</title>
</head>
<body>
	
	<div>
		<div>
			<form id="frm1" action="${contextPath}/employees/search.do">
				<select name="column">
					<option value="E.FIRST_NAME">FIRST_NAME</option>
					<option value="E.PHONE_NUMBER">PHONE_NUMBER</option>
					<option value="D.DEPARTMENT_NAME">DEPARTMENT_NAME</option>
				</select>
				<input list="auto_complete" type="text" name="query" id="query">
				<datalist id="auto_complete"></datalist>	
				<button>검색</button>
			</form>
		</div>
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
					<td>${beginNo - vs.index}</td>
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