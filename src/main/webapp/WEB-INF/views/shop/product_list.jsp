<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<%@ include file="../include/header.jsp"%>
	
	<script>
		$(function() {
			$("#btnAdd").click(function() {
				location.href="${path}/shop/product/write.do";
			});
		});
	</script>

</head>
<body>

	<%@ include file="../include/menu.jsp" %>
	
	<h2>상품목록</h2>
	
	<!-- 관리자만 상품등록 버튼표시  -->
	<c:if test="${sessionScope.admin_userid != null }">
		<button type="button" id="btnAdd">상품등록</button>
	</c:if>
	
	<table border="1" style="width: 500px">
		<tr>
			<th>상품 ID</th>
			<th>상품이미지&nbsp;</th>
			<th>상품명</th>
			<th>가격</th>
		</tr>
	<c:forEach var="row" items="${list}">
		<tr align="center">
			<td>${row.product_id}</td>
			<td><img src="${path}/images/${row.picture_url}" style="width: 100px; height:100px;"></td>
			<td><a href="${path}/shop/product/detail/${row.product_id}">${row.product_name}</a>
				<br>
				<c:if test="${sessionScope.admin_userid != null}">
					<br>
					<a href="${path}/shop/product/edit/${row.product_id}"> [편집] </a>
				</c:if>
			</td>
			<td><fmt:formatNumber value="${row.price}" pattern="#,###" /></td>
		</tr>
	</c:forEach>
	</table>

</body>
</html>