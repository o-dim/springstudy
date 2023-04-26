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
<title>scroll</title>
<script>

	// 전역 변수
	var page = 1;
	var totalPage = 0; // 전체 페이지 (util이 알고있음)
	var timerId; // setTimeout()
	
	// DB에서 목록을 가져오는 함수
	function fnGetEmployees() {
		// 목록 숨기기
		$('.employees').hide();
		// 로딩 보여주기
		$('.loading_wrap').show();
		// 목록을 가져오는 ajax 처리
		$.ajax({
			type : 'get',
			url : '${contextPath}/employee/scroll.do',
			data : 'page=' + page, // page = 1, page=2, page=3... 으로 동작
			dataType : 'json',
			success : function(resData) {
				// 전체 페이지
				totalPage = resData.totalPage;
				page++; // 스크롤을 통해서 목록을 9개씩 가져올때마다 페이지가 증가해야한다
				// 화면에 뿌리기
				$.each(resData.employees, function(i, employee) {
					let str = '<div class="employee">';
					str += '<div>사원번호 : ' + employee.employeeId + '</div>';
					str += '<div>사원 이름 : ' + employee.firstName + ' ' + employee.lastName + '</div>';
					str += '<div>부서 이름 : ' + employee.deptDTO.departmentName + '</div>';
					str += '</div>'
					$('.employees').append(str);
				})
				// 목록 보여주기
				$('.employees').show();
				// 로딩 숨기기
				$('.loading_wrap').hide();
			}
		})
	}
	fnGetEmployees();

	// 스크롤 이벤트
	$(window).on('scroll', function() {
		if(timerId){
			clearTimeout(timerId);
		} // setTimeout() 이 동작했다면 (목록을 가져왔다면) setTimeout()의 재동작을 취소한다 (동일 목록을 가져오는 것을 방지한다)
		timerId = setTimeout(function(){
			let scrollTop = $(this).scrollTop(); // 스크롤 된 길이
			let windowHeight = $(this).height(); // 화면 높이
			let documentHeight = $(document).height(); // 문서높이
			if((scrollTop + windowHeight + 50) >= documentHeight){ // 스크롤이 바닥에 닿기 전 50px 정도 위치(충분히 내려왔다고 판단)
				if(page <= totalPage){ // 마지막 페이지로 넘어가면 목록 요청을 하지 않음
					fnGetEmployees();
				}	
			}
		}, 500); // 500밀리초(0.5초) 후에 function을 수행
	})
</script>
<style>
	div {
		box-sizing: border-box;
	}
	.employees {
		width: 1000px;
		margin: 0 auto;
		display: flex;
		flex-wrap: wrap;
	}
	.employee {
		width : 300px;
		height : 300px;
		border : 1px solid gray;
		margin: 10px;
		text-align: center;
		padding-top: 120px;	
	}
	.loading_wrap {
		display: flex;            /* justify-content, align-items 속성 사용을 위해 설정 */
		justify-content: center;  /* class="loading"의 가로 가운데 정렬 */
		align-items: center;      /* class="loading"의 세로 가운데 정렬 */
		min-height: 80vh;         /* 최소 높이를 화면 높이의 80%로 설정 */
	}
	.loading {
		width: 300px;
		height: 300px;
		background-image: url('../resources/images/loading.gif');
		background-size: 300px 300px;
		background-repeat: no-repeat;
	}
	div {
		box-sizing: border-box;
	}
</style>
</head>
<body>
	<div>
		<a href="${contextPath}/employees/search.form">사원 조회 화면으로 이동</a>
	</div>
	<h1>사원 목록</h1>
	<!--  사원 목록 보여주는 곳  -->
	<div class="employees">
		
	</div>
	<!-- loading.gif 보여주는 곳 -->
	<div class="loading_wrap">
		<div class="loading"></div>
	</div>
</body>
</html>