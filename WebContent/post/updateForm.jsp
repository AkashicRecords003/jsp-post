<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글수정 페이지</title>
	<link rel="stylesheet" href="/apple/static/css/style.css" />
</head>
<body>
<header>
	<h1>글수정 페이지</h1>
</header>
<hr/>
<%@include file="../include/nav.jsp" %>
<section>
	<form action="/apple/post?cmd=updateProc" method="post">
		<input type="hidden" value="${post.id}" name="id" />
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input value="${post.title}" type="text" name="title"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="3" cols="22" name="content">${post.content}</textarea></td>
			</tr>
		</table>
		<button>글수정완료</button>
	</form>
</section>
<footer>
	<p>Created By Apple.</p>
	<p>부산진구 중앙대로 708</p>
</footer>
</body>
</html>

