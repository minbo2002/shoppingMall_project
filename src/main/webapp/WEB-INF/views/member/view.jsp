<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  <!-- 날짜 출력 format 지정 -->  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@ include file="../include/header.jsp" %>
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
		
	<script>
		$(function() {
			
			$("#btnUpdate").click(function() {				// id=btnUpdate인 버튼을 누르면 function 실행
				document.form1.action="/member/update.do";	// "/member/update.do" url로 이동하고
				document.form1.submit();					// 제출
			});
			
			$("#btnDelete").click(function() {		// css 식별자인 #을 사용해서
				if(confirm("삭제하시겠습니까?")) {		// confirm == window.confirm  (window에 있으면 브라우저 내장함수)
					document.form1.action="/member/delete.do";
					document.form1.submit();
				}
			});
		});
	</script>

</head>
<body>

	<%@ include file="../include/menu.jsp" %>
	<h2>회원정보</h2>
	
	<form name="form1" method="post">
		<table border="1" style="width: 400px">		<!-- <table> 태그도 html에서 쓰이는 하나의 객체라고 볼수있음. -->
			<tr>									<!-- border같은건 객체안의 속성. 속성안에 들어가는것이 value -->
				<td>아이디</td>						<!-- 태그안에 style="width: 400px" 이런 식으로 넣는것을 인라인방식 -->
				<td><input name="userid" value="${dto.userid}" readonly></td>	<!-- Controller에서 model에 dto변수로 담았으니 dto로 씀 -->
			</tr>																<!-- readonly는 읽는것만 가능함 -->
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input name="name" value="${dto.name}"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input name="email" value="${dto.email}"></td>
			</tr>
			<tr>
				<td>회원가입 일자</td>
				<td><fmt:formatDate value="${dto.join_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정" id="btnUpdate">
					<input type="button" value="삭제" id="btnDelete">
					<div style="color: red"> ${message} </div>			<!--비밀번호 틀릴경우 message 출력  -->
				</td>
			</tr>
		</table>
	</form>

</body>
</html>