<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    <!-- Core라이브러리 사용하기 위한것  -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  <!-- 날짜 출력 format 지정 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@ include file="../include/header.jsp" %>
</head>
<body>
	<%@ include file="../include/menu.jsp" %>
	<h2>회원목록</h2>
		
	<table border="1" style="width: 700px;">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일자</td>
		</tr>
	<c:forEach var="row" items="${list}">	<!--items은 Controller의 속성값. 즉 전체 list  / var는 개별값  -->
		<tr>
			<td>${row.userid}</td>
			<td><a href="/member/view.do?userid=${row.userid}">${row.name}</a></td>
			<td>${row.email}</td>
			<td><fmt:formatDate value="${row.join_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
	</c:forEach>	
	</table>
	
</body>
</html>