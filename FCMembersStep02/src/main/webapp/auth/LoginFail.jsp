<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Refresh" content="5;url=login.do">
<title>로그인 실패</title>
</head>
<body>
	<jsp:include page="/Header.jsp"/>
	<h1>LogIn Fail</h1>
	<hr>
	<p>로그인 실패 입니다. 이메일 또는 암호가 일치하지 않습니다. <br>
	잠시후에 다시 로그인 화면으로 이동합니다.</p>
	<p>${error}</p>
	<jsp:include page="/Tail.jsp"/>
</body>
</html>