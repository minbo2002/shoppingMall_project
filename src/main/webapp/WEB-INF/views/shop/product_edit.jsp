<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<%@ include file="../include/header.jsp"%>

	<script>
		function product_update() {
			
			document.form1.action="${path}/shop/product/update.do";
			document.form1.submit();
		}
		
		function product_delete() {
			if(confirm("삭제하시겠습니까?")) {
				
				document.form1.action="${path}/shop/product/delete.do";
				document.form1.submit();
			}
		}
	</script>

</head>
<body>

	<%@ include file="../include/menu.jsp" %>
	
	<h2>상품 정보 편집</h2>
	
	<form id="form1" name="form1" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>상품명</th>
				<td><input name="product_name" value="${dto.product_name}"></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input name="price" type="number" value="${dto.price}"></td>
			</tr>
			<tr>
				<th>상품설명</th>
				<td><textarea rows="5" cols="60" name="description" id="description">${dto.description}</textarea></td>
			</tr>
			<tr>
				<th>상품 이미지</th>
				<td>
					<img src="${path}/images/${dto.picture_url}" style="width: 300px; height: 300px;"> <br>
					<input type="file" name="file1">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="product_id" value="${dto.product_id}">	<!--상품코드값이  hidden으로 넘어감  -->
					<input type="button" value="수정 " onclick="product_update()">
					<input type="button" value="삭제 " onclick="product_delete()">
					<input type="button" value="목록" onclick="location.href='${path}/shop/product/list.do'">
				</td>
			</tr>
		</table>							
	</form>

</body>
</html>