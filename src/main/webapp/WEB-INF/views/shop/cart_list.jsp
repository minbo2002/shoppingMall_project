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
		$("#btnList").click(function() {
			location.href="${path}/shop/product/list.do";
		});
		$("#btnDelete").click(function() {
			if(confirm("장바구니를 비우시겠습니까?")) {
				location.href="${path}/shop/cart/deleteAll.do";
			}
		});
		$("#btnBuy").click(function() {
			if(confirm("구매하시겠습니까?")) {
				lcoation.href="${path}/shop/cart/buy.do";
			}
		})
	});
	</script>

</head>
<body>

	<%@ include file="../include/menu.jsp" %>
	
	<h2>장바구니</h2>
	
	<c:choose>
		<c:when test="${map.count == 0 }">
			장바구니가 비었습니다.
		</c:when>
		<c:otherwise>
			<form action="${path}/shop/cart/update.do" id="form1" name="form1" method="post">
				<table border="1" style="width: 400px;">
					<tr>
						<th>상품명</th>
						<th>단가</th>
						<th>수량</th>
						<th>금액</th>
						<th>&nbsp;</th>
					</tr>
					
				<c:forEach var="row" items="${map.list}">
					<tr align="center">
						<td>${row.product_name}</td>
						<td><fmt:formatNumber value="${row.price}" pattern="#,###,###" /></td>
						<td><input type="number" name="amount" style="width: 30px;"
							 	value="<fmt:formatNumber value="${row.amount}" pattern="#,###,###" />" >
							<input type="hidden" name="cart_id" value="${row.cart_id}">
						 </td>
						<td><fmt:formatNumber value="${row.money}" pattern="#,###,###" /></td>
						<td><a href="${path}/shop/cart/delete.do?cart_id=${row.cart_id}"> [삭제] </td>
					</tr>
				</c:forEach>
				
					<tr>
						<td colspan="5" align="right">
							장바구니 금액 합계 : <fmt:formatNumber value="${map.sumMoney}" pattern="#,###,###" /> <br>
							배송료 : ${map.fee} <br>
							총합계 : <fmt:formatNumber value="${map.sum}" pattern="#,###,###" />
						</td>
					</tr>	
				</table>
				
				<button id="btnUpdate"> 수정 </button>
				<button type="button" id="btnDelete"> 장바구니 비우기 </button>
				<button type="button" id="btnBuy">구매하기</button>
			</form>
		</c:otherwise>
	</c:choose>
	<button type="button" id="btnList"> 상품목록 </button>

</body>
</html>