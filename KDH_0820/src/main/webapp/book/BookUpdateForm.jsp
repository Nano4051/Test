<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 조회 및 수정</title>
</head>
<body>
	<h1>도서 수정</h1><hr>
	<form action="update.do" method = "post">
		<table>
			<tr><th>번호</th><td><input type="text" name="id" value ="${book.id}" readonly></td></tr>
			<tr><th>책 제목</th><td><input type="text" name="title" value ="${book.title}"></td></tr>
			<tr><th>저자</th><td><input type="text" name="author" value ="${book.author}"></td></tr>
			<tr><th>출판사</th><td><input type="text" name="publisher" value ="${book.publisher}"></td></tr>
			<tr><th>발행일</th><td><input type="date" name="pub_date" value ="${book.pub_date}"></td></tr>
			<tr><th>ISBN</th><td><input type="text" name="isbn" value ="${book.isbn}"></td></tr>
			<tr><th>카테고리</th><td>
					<select name="category">
						<option value ="문학" ${book.category == "문학" ? "selected" : ""}>문학</option>
						<option value ="예술" ${book.category == "예술" ? "selected" : ""}>예술</option>
						<option value ="과학" ${book.category == "과학" ? "selected" : ""}>과학</option>
						<option value ="인문" ${book.category == "인문" ? "selected" : ""}>인문</option>
					</select>
			<tr><th>가격</th><td><input type="text" name="price" value ="${book.price}"></td></tr>
			<tr><th>재고</th><td><input type="text" name="stock" value ="${book.stock}"></td></tr>
			<tr><th>등록일</th><td><input type="date" name="cre_date" value ="${book.cre_date}"readonly></td></tr>
			<tr><th>수정일</th><td><input type="date" name="mode_date" value ="${book.mode_date}"></td></tr>
			<tr><th>메모</th><td><input type="text" name="memo" value ="${book.memo}"></td></tr>
		</table>
		<input type="submit" value="수정">
		<a href="delete.do?id=${book.id}">[삭제]</a>
		<input type="reset" value="취소">
	</form>
</body>
</html>