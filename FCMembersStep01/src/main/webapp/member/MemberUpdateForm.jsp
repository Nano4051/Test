<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "dto.Member"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회 및 수정</title>
</head>
<body>
	<jsp:include page="/Header.jsp"/>
	<h1>회원 조회 및 수정</h1><hr>
	<form action ='update.do' method = 'post'>
		<table>
			<tr><th>번호 : <input type='text' name ='mno' value = '${member.mno }' readonly></th></tr>
			<tr><th>이름 : <input type='text' name ='mname' value = '${member.mname }'></th></tr>
			<tr><th>E-Mail : <input type='text' name ='email' value = '${member.email }'></th></tr>
			<tr><th>생성일자 : <input type='text' name ='cre_date' value = '${member.cre_date }' readonly></th></tr>
			<tr><th>수정일자 : <input type='text' name ='mod_date' value = '${member.mod_date }' readonly></th></tr>
		</table>
		<input type='submit' value = '수정'>
		<input type='reset' value = '취소'>	
	</form>
	<jsp:include page="/Tail.jsp"/>
</body>
</html>