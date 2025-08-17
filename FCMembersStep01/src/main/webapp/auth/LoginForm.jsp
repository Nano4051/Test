<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<jsp:include page="/Header.jsp"/>
	<h1>LogIn</h1>
	<hr>
	<form action ="login.do" method="post">
		이메일 : <input type="text" name="email"><br>
		비밀번호 : <input type="text" name="pwd"><br>
		<input type="submit" name="LOGIN" value="로그인">
		<input type="reset" name="CANCEL" value = "취소">
	</form>
	<jsp:include page="/Tail.jsp"/>
</body>
</html>