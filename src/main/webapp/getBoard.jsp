<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "com.mysite.board.BoardDTO" %>    
    
<%
	//세션에 저장된 변수의 값을 불러옴. 
	BoardDTO board = (BoardDTO)	session.getAttribute("board"); 

	//out.println(board.getSeq() + "<br> <br>"); 
	//out.println(board.getTitle()+ "<br> <br>"); 
	//out.println(board.getWrite()+ "<br> <br>"); 
	//out.println(board.getContent()+ "<br> <br>"); 
	//out.println(board.getCnt()+ "<br> <br>"); 
	

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세</title>
<style> 
	div {
		width : 700px; 
		margin : 0 auto; 
	}
	
	td {
		padding :10px; 
	}

</style>
</head>
<body>

	<div> 
		<h1> 글 상세 페이지 - Form 태그 내부에 value 값에 출력 (수정을 바로 할 수 있도록) </h1>
		<hr> 
		<br><br> 
		
		<form action = "updateBoard.do" method="post"> 
		
			<!--  글 수정시 seq 변수 값을 서버로 전송  -->
			<input type = "hidden" name = "seq" value = "<%= board.getSeq() %>"> 
			<input type = "hidden" name = "write" value = "<%= board.getWrite() %>"> 
			
			<table border="1px" cellspacing="0" cellpadding="0">
				<tr><td bgcolor = "orange">제목</td> 
					<td> <input type ="text" name="title" value = "<%= board.getTitle() %>"></td> 
				</tr>
				<tr><td bgcolor = "orange">작성자</td> 
					<td> <%= board.getWrite() %> </td> 
				</tr>
				<tr><td bgcolor = "orange">내용</td> 
					<td><textarea rows="10" cols="50" name = "content"><%= board.getContent() %>
					</textarea> </td> 
				</tr>
				<tr><td bgcolor = "orange">등록일</td> 
					<td> <%= board.getRegdate() %> </td> 
				</tr>
				<tr><td bgcolor = "orange">조회수</td> 
					<td> <%= board.getCnt() %> </td> 
				</tr>
				<tr><td colspan = "2" align ="center"> <input type="submit" value = " 글 수정 하기"> </td> 
				</tr>
		
			</table>
		</form>
		
		<br> <br> 
		<a href = "getBoardList.do"> 글 목록 보기 </a>
		<br> <br> 
		<a href = "insertBoard.jsp"> 글쓰기 </a>
		<br> <br> 
		<a href = "deleteBoard.do?seq=<%= board.getSeq() %>&write=<%= board.getWrite() %>"> 글삭제 </a>
		<br> <br> 
		<a href = "/JSP_Study_MVC_M2"> 처음 페이지로 이동 </a>
		
		
		
		
	</div>
</body>
</html>