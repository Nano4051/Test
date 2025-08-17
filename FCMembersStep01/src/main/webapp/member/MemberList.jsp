<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import ="java.util.ArrayList,dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	<jsp:include page="/Header.jsp"/>
	<h1>회원 LIST</h1>
	<hr>
	<p><a href ="add.do">신규 등록</a></p>
	<table>
		<tr><th>번호</th><th>이름</th><th>이메일</th><th>등록일자</th></tr>
		<c:forEach var="m" items="${members}">
			<tr>
				<td>${m.mno }</td>
				<td><a href = "update.do?mno=${m.mno}">${m.mname }</a></td>
				<td>${m.email }</td>
				<td>${m.cre_date }</td>
				<td><a href = "delete.do?mno=${m.mno}">[삭제]</a></td>				
			</tr>
		</c:forEach> 	
	</table>
	<jsp:include page="/Tail.jsp"/>
</body>
</html>