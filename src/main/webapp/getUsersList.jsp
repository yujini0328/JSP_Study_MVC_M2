<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*" %>    
<%@ page import="com.mysite.users.UsersDTO" %>    
    
<%
	List<UsersDTO> usersList = new ArrayList<UsersDTO>(); 

	usersList = (List<UsersDTO>) session.getAttribute("usersList"); 

%> 
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style> 
	div {
		width : 750px; 
		margin : 0 auto ; 
	}

</style>
</head>
<body>

	<div>
	<h1> 사용자 정보 리스트 출력 </h1>
		<table border="1px" cellpadding = "0" cellspacing = "0" width = "700px"> 
			<tr>
				<th bgcolor="orange" > 아이디 (ID)</th>
				<th> 패스워드 </th>
				<th> 이름 </th>
				<th> 룰 (권한) </th>
			 </tr>
		
			<% 
				for (UsersDTO k : usersList){
			%>
			<!--  루프 시작 -->
			<tr> 
				<td> <%= k.getId() %> </td>
				<td> <%= k.getPassword() %></td>
				<td> <%= k.getName() %></td>
				<td> <%= k.getRole() %></td>
			</tr>
			<!--  루프 끝  -->
			<%
				}
			
			//session 객체의 저장된 변수 제거  : 모두 사용후 
			session.removeAttribute("usersList"); 
			
			%>
		</table>
		
			<br> <br> 
			<a href = "insertUsers.jsp"> 새 사용자 등록 </a>
	</div>
</body>
</html>