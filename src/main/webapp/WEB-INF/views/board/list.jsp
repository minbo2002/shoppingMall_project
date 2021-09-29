<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@ include file="../include/header.jsp"%>
		
	<script>
		$(function(){
			$("#btnWrite").click(function() {
				location.href="${path}/board/write.do";
			});
		});
		
		function list(page) {  // 요청한 page 번호
			location.href="${path}/board/list.do?curPage="+page	 // BoardController의 @requestParam int curPage에 전달됨
					+"&search_option=${map.search_option}"
					+"&keyword=${map.keyword}";
		}
	</script>
	
</head>
<body>
	<%@ include file="../include/menu.jsp" %>
	<h2>리뷰</h2>
				
		<form name="form1" method="post" action="${path}/board/list.do">	<!--searach_optoin 과 keyword 가 list.do로 전달 -->
			<select name="search_option">
				<c:choose>
					<c:when test="${map.search_option == 'all'}">
						<option value="all" selected> 이름+내용+제목 </option>
						<option value="writer"> 이름 </option>
						<option value="content"> 내용 </option>
						<option value="title"> 제목 </option>
					</c:when>
					<c:when test="${map.search_option == 'writer'}">
						<option value="all"> 이름+내용+제목 </option>
						<option value="writer" selected> 이름 </option>
						<option value="content"> 내용 </option>
						<option value="title"> 제목 </option>
					</c:when>
					<c:when test="${map.search_option == 'content'}">
						<option value="all"> 이름+내용+제목 </option>
						<option value="writer"> 이름 </option>
						<option value="content" selected> 내용 </option>
						<option value="title"> 제목 </option>
					</c:when>
					<c:when test="${map.search_option == 'title'}">
						<option value="all"> 이름+내용+제목 </option>
						<option value="writer"> 이름 </option>
						<option value="content"> 내용 </option>
						<option value="title" selected> 제목 </option>
					</c:when>
				</c:choose>
			</select>
			
			<input name="keyword" value="${map.keyword}">
			<input type="submit" value="조회">
		</form>
	
	<button type="button" id="btnWrite">글쓰기</button>
	${map.count}개의 리뷰가 있습니다.
	
	<table border="1" style="width: 600px;">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>이름</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
	<c:forEach var="row" items="${map.list}">
		<tr>
			<td>${row.bno}</td>
			<td>
				<a href="${path}/board/view.do?bno=${row.bno}
					&curPage=${map.pager.curPage}
					&search_option=${map.search_option}
					&keyword=${map.keyword}">${row.title} </a>	<!-- curPage, search_option, keyword 같이 보내줘야 페이지 및 검색이 풀리지않음 -->
				<!--댓글 개수 -->
				<c:if test="${row.cnt > 0}">
					<span style="color: red;">( ${row.cnt} )</span>
				</c:if>
			</td>
			<td>${row.name}</td>
			<td><fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td>${row.viewcnt}</td>
		</tr>
	</c:forEach>	
	
	<!--페이지 네비게이션  -->
		<tr>
			<td colspan="5" align="center">
				<c:if test="${map.pager.curBlock > 1}">
					<a href="javascript:list('1')">[처음]</a>
				</c:if>
				<c:if test="${map.pager.curBlock > 1}">
					<a href="javascript:list('${map.pager.prevPage}')">[이전]</a>
				</c:if>
				
				<c:forEach var="num" begin="${map.pager.blockBegin}" end="${map.pager.blockEnd}">
					<c:choose>
						<c:when test="${num == map.pager.curPage}">
							<!--현재 페이지 -->
							<span style="color: red;">${num}</span>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="javascript:list('${num}')">${num}</a>&nbsp;	<!-- 페이지 번호 num을 클릭하면 list 함수 호출  -->	
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:if test="${map.pager.curBlock <= map.pager.totBlock}">
					<a href="javascript:list('${map.pager.nextPage}')">[다음]</a>
				</c:if>
				<c:if test="${map.pager.curPage <= map.pager.totPage}">
					<a href="javascript:list('${map.pager.totPage}')">[끝]</a>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>