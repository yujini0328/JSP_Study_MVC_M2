<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>새 사용자 등록</title>
</head>
<body>
	<center>
		<h1>새 사용자 등록</h1>
		<a href="logout.do">Log-out</a>
		<hr>
		<form action="insertUsers.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange" width="70">아이디</td>
					<td align="left"><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">패스워드</td>
					<td align="left"><input type="text" name="password" size="10" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">이름</td>
					<td align="left"><input type="text" name="name" size="10" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">룰 (권한)</td>
					<td align="left"><input type="text" name="role" size="10" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value=" 새 사용자 등록 " /></td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="getUsersList.do"> 사용자 목록 가기 </a>
	</center>
</body>
</html>