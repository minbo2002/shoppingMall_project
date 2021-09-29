<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@ include file="../include/header.jsp"%>
	
	<script src="${path}/ckeditor/ckeditor.js"></script>
	
	<script>
		$(function() {
			listReply("1");  // 기존의 댓글을 나오게하기
			$("#btnReply").click(function() {
				reply();
			});
			$("#btnList").click(function() {
				location.href="${path}/board/list.do";
			});
		});
		
		function reply() {
			var replytext = $("#replytext").val();  // 댓글내용
			var bno = "${dto.bno}";					// 원글 번호
			var param = {"replytext" : replytext, "bno" : bno};  // 파라미터 형식
			$.ajax({
				type: "post",
				url: "${path}/reply/insert.do",
				data: param,
				success: function() {
					alert("댓글이 등록되었습니다");
					listReply("1");  // 댓글 페이지 나누기
				}
			});
		}
		
		function listReply(num) {
			$.ajax({
				type: "post",
				url: "${path}/reply/list.do?bno=${dto.bno}&curpage="+num,
				success: function(result) {
					console.log(result);
					$("#listReply").html(result);
				}
			});
		}
	</script>

</head>
<body>
	<%@ include file="../include/menu.jsp" %>
	<h2>게시물 보기</h2>
	
	<form id="form1" name="form1" method="post">
		<div>작성일자 : <fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd a HH:mm:ss" /></div>
		<div>조회수 : ${dto.viewcnt}</div>
		<div>이름 : ${dto.name}</div>
		<div>제목 : <input name="title" value="${dto.title}"></div>
		<div style="width: 80%;">내용 : <textarea rows="3" cols="80" id="content" name="content">${dto.content}</textarea></div>

		
		<div id="uploadedList"></div>
		<div class="fileDrop"></div>
		<div>
			<input type="hidden" name="bno" value="${dto.bno}">
			<c:if test="${sessionScope.userid == dto.writer}">
				<button type="button" id="btnUpdate">수정</button>
				<button type="button" id="btnDelete">삭제</button>
			</c:if>
				<button type="button" id="btnList">목록</button>
		</div>
	</form>
	
	<!--댓글 쓰기 -->
	<div style="width: 700px; text-align: center;">
		<c:if test="${sessionScope.userid != null}">
			<textarea rows="5" cols="80" id="replytext" placeholder="댓글을 작성하세요"></textarea> <br>
			<button type="button" id="btnReply">댓글쓰기</button>
		</c:if>
	</div>
	<!--댓글 목록  -->
	<div id="listReply"></div>
	
</body>
</html>