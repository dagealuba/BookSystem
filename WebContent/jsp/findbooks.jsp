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
<link rel = "stylesheet" href = "../css&js/css/main.css">


</head>
<body class = "container">
	<div class = "row">
		<div class = "col-md-1">
			<h4 >找书</h4>
		</div>
		
		<form role = "form" class = "form-inline"  style="padding-top:0.25%">
			<div class="form-group">
				<label for = "bookName">书名:</label>
				<input type = "text" id = "bookName" name = "bookName" class = "form-control" placeholder="输入想要查找的书名">
			</div>
			
			<div class = "form-group">
				<label for="bookAuthor">作者:</label>
				<input type="text" id = "bookAuthor" name = "bookAuthor" class = "form-control" placeholder="输入想要查找的作者">
			</div>
			
			<div class = "form-group">
				<button type="button" id = "find-books" class="btn btn-success">查找</button>
			</div>
			
			<div class = " form-group pull-right">
				<label for = "page_lines" class = "text-sm">每页显示的条目:</label>
				<select class = "from-control" id = "page_lines">
					<option id = "1">1</option>
					<option id = "2">2</option>
					<option id = "3">3</option>
				</select>
			</div>
		</form>
		
		
	</div>
	
	<div class = "row">
		<table class = "table">
		<thead>
			<tr>
				<th>书名</th>
				<th>作者</th>
				<th>出版社</th>
				<th>价格/￥</th>
				<th>库存</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
			
		</tbody>
		</table>
		<div id = "table-foot">
		</div>
		<div id = "error">
		</div>
		
	</div>

<!-- js -->
	<script type="text/javascript" src = "../bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src ="../css&js/js/getBooks.js"></script>
	
</body>
</html>