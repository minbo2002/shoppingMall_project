<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객의소리 (메모장)</title>

	<%@ include file="../include/header.jsp"%>
	
	<script>
		function memo_view(idx) {
			location.href="${path}/memo/view/"+idx;
			// http://localhost:8081/memo/view/3       vs   http://localhost:8081/memo/view?idx=3
			// idx를 파라미터가 아닌 고유주소로써 넘긴다 			    	       idx를 파라미터로 넘긴다			
			// (controller에서 숫자를 @PathVariable로 받을수있음)	
		}	
	</script>
	
</head>
<body>
	<%@ include file="../include/menu.jsp" %>	<!-- 스크립트릿  ! @ = 차이 -->
	
	<form action="${path}/memo/insert.do" method="post">
		고객이름: <input name="writer" size="10">
		건의사항 : <input name="memo" size="40">
		<input type="submit" value="확인">
	</form>
	
	<table border="1" style="width: 500px;">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>메모</th>
			<th>날짜</th>
		</tr>
		<c:forEach var="row" items="${list}">
		<tr>
			<td>${row.idx}</td>
			<td>${row.writer}</td>
			<td><a href="#" onclick="memo_view('${row.idx}')">${row.memo}</a></td>
			<td><fmt:formatDate value="${row.post_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>	
		</c:forEach>
	</table>

</body>
</html>