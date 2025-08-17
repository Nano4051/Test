<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
</head>
<body>
	<jsp:include page="/Header.jsp"/>
	<h1>신규 등록</h1><hr>
	<table>
		<form action ="add.do" method = "post">
		<tr><th>이름</th><td><input type='text' name ='mname'></td></tr>
		<tr><th>비밀번호</th><td><input type='password' name ='pwd'></td></tr>
		<tr><th>E-mail</th><td><input type='text' name ='email'></td></tr>
		<tr><th colspan="2"><input type='submit' value = '추가'><input type='reset' value = '취소'></th></tr>
		</form>
	</table>
	<jsp:include page="/Tail.jsp"/>
</body>
</html>