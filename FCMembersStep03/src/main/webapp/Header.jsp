<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import ="dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<c:if test="${empty loginMember.mname }"> 
		<div style="background-color:blue;color:white;height:30px;padding:10px;">
			<a style ="color:white" href="<%=request.getContextPath()%>/member/list.do">SPMS</a> 
			<span style="float:right;">
				<a style ="color:white"href="<%=request.getContextPath()%>/auth/login.do"> LOGIN</a>
			</span>
		</div>
	</c:if>
	<c:if test="${!empty loginMember.mname }">
		<div style="background-color:blue;color:white;height:30px;padding:10px;">
			<a style ="color:white" href="<%=request.getContextPath()%>/member/list.do">SPMS</a> 
			<span style="float:right;">
				${loginMember.mname}님 반갑습니다
			<a style ="color:white" href="<%=request.getContextPath()%>/auth/logout.do"> LOGOUT</a>
			</span>
		</div>
	</c:if>
</body>
</html>