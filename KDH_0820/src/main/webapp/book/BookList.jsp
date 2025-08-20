<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보 목록</title>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
        text-align: center;
    }
    th, td {
        padding: 8px;
    }
    th{
    	border-top: 2px solid black;   
  		border-bottom: 2px solid black;
  		border-left: none;           
  		border-right: none;         
  		padding: 8px;                 
    }
</style>
</head>
<body>
    <h1 style="text-align:center;"><a href="list.do">도서 정보 목록</a></h1>
    <div style="margin: 20px; padding:0;" >
        <span style="float:left;">
            <a href="add.do">신규 등록</a>
        </span>
        <span style="float:right;">
            <form action="${pageContext.request.contextPath}/book/search.do" method="get">
                책 제목 :
                <input style="margin-left:10px;" type="text" name="searchbook" value="${param.searchbook}" placeholder="도서 검색">
                <input type="submit" value="검색">
            </form>
        </span>
    </div>
    <div style="margin-top:70px;">
        <table>
            <tr>
                <th>번호</th>
                <th>책제목</th>
                <th>저자</th>
                <th>카테고리</th>
                <th>출판사</th>
                <th>가격</th>
            </tr>
            <c:forEach var="b" items="${book}">
                <tr>
                    <td>${b.id}</td>
                    <td><a href="update.do?id=${b.id}">${b.title}</a></td>
                    <td>${b.author}</td>
                    <td>${b.category}</td>
                    <td>${b.publisher}</td>
                    <td>${b.price}</td>
                </tr>
            </c:forEach>
        </table>
       <hr style="border: 1px solid black;" >
    </div>
</body>
</html>
