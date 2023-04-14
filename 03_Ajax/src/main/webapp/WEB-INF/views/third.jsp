<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	function fn1() {
		$.ajax({
			// 요청
			type : 'post', // 서버로 보내는 데이터를 요청본문(request body)에 저장해서 보낸다
			url : '${contextPath}/third/ajax1',
			data : JSON.stringify({ // 별도 라이브러리가 필요하진 않음 (객체 -> JSON(문자열)로 변환하기). 그러나 파라미터 이름이 없음에 주의한다.
				// 그렇기에 서버측에서 파라미터로 받을 수 없음
				// service에 request로 선언할 필요가 없음
				'name' : $('#name').val(),
				'tel' : $('#tel').val()
			}),
			contentType : 'application/json', // 서버로 보내는 데이터의 type을 서버에 알려준다 .
			dataType : 'json',
			success : function(resData) { // resData = {"name" : "somin", "tel" : "0407"}
				let str = '<ul>';
				str += '<li>' + resData.name;
				str += '<li>' + resData.tel;
				$('#result').html(str);
			},
			error : function(jqXHR) {
				if(jqXHR.status == 400){
					alert('이름과 전화번호는 필수입니다.');
				}
			}
			
		})
	}
	function fn2() {
		$.ajax({
			type : 'post',
			url : '${contextPath}/third/ajax2',
		})
	}
</script>
</head>
<body>

	<div>
		<form id="frm">
			<div>
				<label for="name">name</label>
				<input id="name" name="name">
			</div>
			<div>
				<label for="tel">tel</label>
				<input id="tel" name="tel">
			</div>
			<div>
				<input type="button" value="submit1" onclick="fn1()">
				<input type="button" value="submit2" onclick="fn2()">
			</div>
		</form>
	</div>
	
	<hr>
	<div id="result"></div>

</body>
</html>