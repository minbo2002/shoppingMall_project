<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
	<%@ include file="include/header.jsp" %>
</head>
<body>
	<%@ include file="include/menu.jsp" %>
	
	<c:choose>
		<c:when test="${sessionScope.userid != null }">
		<h2>${sessionScope.name} (${sessionScope.userid}) 님의 방문을 환영합니다.</h2>
		</c:when>
	
		<c:when test="${sessionScope.admin_userid != null }">
			<h2>${sessionScope.admin_name} (${sessionScope.admin_userid}) 님의 방문을 환영합니다.</h2>
		</c:when>
	</c:choose>
	
	<h1>
		Hello world!  
	</h1>
	
	<P>  The time on the server is ${serverTime}. </P>
	
	<!-- 배포 디렉토리 확인 -->
	<%=application.getRealPath("/") %>
</body>
</html>
