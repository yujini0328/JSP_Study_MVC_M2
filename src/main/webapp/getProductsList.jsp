<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "com.mysite.products.ProductsDTO" %>
<%@ page import = "java.util.*" %>
<%
	List<ProductsDTO> productsList = (List) session.getAttribute("productsList");

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세 정보 출력 </title>
<style> 
	div {
		width : 950px; 
		margin : 0 auto ; 
	}
	td,th {
		padding : 2px; 
	}

</style>
</head>
<body>
	<div>
	<h1> 글 목록  </h1>
	<hr> 
	
		<table border="1px" cellpadding = "0" cellspacing="0" width = "940px"> 
		<tr>
			<th bgcolor="orange" width = "80px"> 제품 번호  </th>
			<th bgcolor="orange" width = "200px"> 제품 이름  </th>
			<th bgcolor="orange" width = "100px"> 제품 종류 </th>
			<th bgcolor="orange" width = "150px"> 제품 가격 </th>
			<th bgcolor="orange" width = "100px"> 제품 설명 </th>
			<th bgcolor="orange" width = "100px"> 제품 갯수 </th>
			<th bgcolor="orange" width = "100px"> 제품 등록 날짜 </th>
		 </tr>
		 
		 <!-- 루프시작  -->
		 	<%
		 		for (ProductsDTO k : productsList) {
		 	%>
		 	<tr>
			<td bgcolor="orange" width = "80px"> <%= k.getP_code() %>  </td>
			<td bgcolor="orange" width = "200px"> <%= k.getP_name() %>제품 이름  </td>
			<td bgcolor="orange" width = "100px"> <%= k.getP_kind() %>제품 종류 </td>
			<td bgcolor="orange" width = "150px"> <%= k.getP_price() %>제품 가격 </td>
			<td bgcolor="orange" width = "100px"> <%= k.getP_content() %>제품 설명 </td>
			<td bgcolor="orange" width = "100px"> <%= k.getP_quantity() %>제품 갯수 </td>
			<td bgcolor="orange" width = "100px"> <%= k.getIndate() %>제품 등록 날짜 </td>
		 </tr>
		 
		 	<%
		 		} 
		 	%>		 
		 
		 <!-- 루프시작  -->
		 </table>
	
	</div>
</body>
</html>