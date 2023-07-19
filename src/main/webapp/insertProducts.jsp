<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
		String sessionId = (String) session.getAttribute("id"); 
		String sessionRole = (String) session.getAttribute("role");
		
		
%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 등록 페이지 </title>
<style>
	div {
		width : 700px;
		margin: 0 auto ; 
	}
	td {
		padding: 10px; 
	}

</style>
</head>
<body>
<div>
		<h1> 제품 등록 페이지 (관리자만 접근가능.)</h1>
		<hr> 
		
		<% 
		
			if (sessionRole != null && sessionRole.equals("Admin")){
		%>

		<a href="logout.do">Log-out</a>
		<hr>
		<form action="insertProducts.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange" width="70">제품 코드 </td>
					<td align="left"><input type="text" name="p_code" />(* 정수만 입력해 주세요)</td>
				</tr>
				<tr>
					<td bgcolor="orange">제품 이름</td>
					<td align="left"><input type="text" name="p_name"  /></td>
				</tr>
				<tr>
					<td bgcolor="orange">제품 종류</td>
					<td align="left">
						<select name="p_kind">
							<option value="A"> 아이폰 </option>
							<option value="B"> 갤럭시 </option>
							<option value="C"> 안드로이드 </option>
							<option value="D"> TV </option>
							<option value="E"> 세탁기 </option>
						
						</select>
					
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">제품 가격</td>
					<td align="left"><input type="text"  name="p_price" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">제품 상세 정보</td>
					<td align="left"><textarea name="p_content" cols="40" rows="10"></textarea></td>
				</tr>
								<tr>
					<td bgcolor="orange">제품 수량</td>
					<td align="left"><input type="text"  name="p_quantity"  /></td>
				</tr>
								
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value=" 새 제품 등록 " /></td>
				</tr>
			</table>
		</form>
		
		<%
			} else {
		
		%>
		<h3> 해당 페이지는 관리자만 접근 가능합니다. </h3>
		<h3> 관라자로 <a href = "loginForm.jsp"> 로그인 </a> 해주세요. </h3>
		
		<%
			}
		%>
		
</div>
</body>
</html>