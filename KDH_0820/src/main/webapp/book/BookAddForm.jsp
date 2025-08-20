<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 도서 등록</title>
</head>
<body>
		<h1>신규 등록</h1>
		<table>
			<form acrion="add.do" method ="post">
				<tr><th>책 제목</th><td><input type="text" name ="title"></td></tr>
				<tr><th>저자</th><td><input type="text" name ="author"></td></tr>
				<tr><th>출판사</th><td><input type="text" name ="publisher"></td></tr>
				<tr><th>발행일</th><td><input type="date" name ="pub_date"></td></tr>
				<tr><th>ISBN</th><td><input type="text" name ="isbn"></td></tr>
				<tr><th>카테고리</th><td>
					<select name="category">
						<option value ="문학" ${book.category == 0 ? "selected" : ""}>문학</option>
						<option value ="예술" ${book.category == 1 ? "selected" : ""}>예술</option>
						<option value ="과학" ${book.category == 2 ? "selected" : ""}>과학</option>
						<option value ="인문" ${book.category == 3 ? "selected" : ""}>인문</option>
					</select>
				<tr><th>가격</th><td><input type="text" name ="price"></td></tr>
				<tr><th>재고</th><td><input type="text" name ="stock"></td></tr>
				<tr><th>등록일</th><td><input type="date" name ="cre_date"></td></tr>
				<tr><th>수정일</th><td><input type="date" name ="mode_date"></td></tr>
				<tr><th>메모</th><td><textarea rows="5" cols="20" name ="memo"></textarea></td></tr>
				<tr><th colspan="2"><input type='submit' value = '추가'><input type='reset' value = '취소'></th></tr>
			</form>
		</table>
</body>
</html>