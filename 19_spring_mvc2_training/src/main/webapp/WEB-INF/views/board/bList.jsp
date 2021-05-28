<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bList</title>
</head>
<body>
	<div align="center">
	<h1>전체 게시글 리스트</h1>
	<table border="1">
			<tr>
				<td>게시글 번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
		<c:forEach var="bdto" items="${boardList }">
		<tr>
			<td>${bdto.num }</td>
			<td>${bdto.subject}</td>
			<td>${bdto.writer}</td>
			<td>${bdto.regDate }</td>
			<td>${bdto.readCount }</td>
		</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>