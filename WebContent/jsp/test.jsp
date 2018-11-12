<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
	<script type="text/javascript" src = "../bootstrap-3.3.7/jQuery/jquery.min.js"></script>
<!-- css -->
<link rel = "stylesheet" href = "../bootstrap-3.3.7/css/bootstrap.min.css">
</head>
<body>
	<form action="../FindBooksServlet" method="post">
		<input type = "text" id = "bookName" name = "bookName" placeholder= "书名">
		<input type="text" id = "author" name = "bookAuthor" placeholder="作者">
		<button type="submit">查询</button>
	</form>


<!-- js -->
	<script type="text/javascript" src = "../bootstrap-3.3.7/js/bootstrap.min.js"></script>
</body>
</html>