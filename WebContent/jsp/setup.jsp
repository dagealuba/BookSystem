<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "entity.User" %>
<% User user = (User)request.getSession().getAttribute("user");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>setup</title>
<!-- jQuery -->
	<script type="text/javascript" src = "../bootstrap-3.3.7/jQuery/jquery.min.js"></script>
<!-- css -->
<link rel = "stylesheet" href = "../bootstrap-3.3.7/css/bootstrap.min.css">

<style type="text/css">
	.tab-content{
		padding-left:17%;
	}
	.form-control{
		
	}
</style>

</head>
<body>
	<div style="width:15%;position:fixed">
	<ul id = "menu" class = "nav nav-tab">
		<li>
			<a href="#userSetup" data-toggle="tab">用户管理</a>
		</li>
		
		<li>
			<a href="#" id = "bookSetup" class ="dropdown-toggle" data-toggle="dropdown">
				书籍管理<b class = "caret"></b>
			</a>
			
			 <ul class="dropdown-menu" role="menu" aria-labelledby="bookSetup">
			 	<li>
			 		<a href = "#addBooks" data-toggle="tab">增加书籍</a>
			 	</li>
			 	<li>
			 		<a href = "#updateBooks" data-toggle="tab">修改书籍</a>
			 	</li>
			 </ul>
		</li>
	</ul>
	</div>
	
	<div  class = "tab-content">
		<div id = "userSetup" class="tab-pane fade in active">
			<table class = "table">
				<thead>
					<tr>
						<th>Id</th>
						<th>用户名</th>
						<th>邮箱</th>
						<th>用户类型</th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div class="tab-pane fade" id="addBooks">
			<div style = "width:60%">
				<form role = "form" class = "form-horizontal">
					<div class = "form-group">
						<label for = "bookName" class = "control-label col-sm-2">书名: </label>
						<div class = "col-sm-10">
						<input class = "form-control" id = "bookName" name = "bookName" type="text" placeholder = "书名">
						</div>
					</div>
					
					<div class = "form-group">
						<label for = "bookAuthor" class = "control-label col-sm-2">作者:</label>
						<div class = "col-sm-10">
						<input id = "bookAuthor" class = "form-control" name = "bookAuthor" type = "text" placeholder = "作者">
						</div>
					</div>
					
					<div class = "form-group">
						<label for = "publishName" class = "control-label col-sm-2">出版社:</label>
						<div class = "col-sm-10">
						<input type="text" class = "form-control" id = "publishName" name = "publishName" placeholder = "出版社">
						</div>
					</div>
					
					<div class = "form-group">
						<div class= "col-sm-6">
						<label for = "bookPrice" class = "control-label col-sm-4">价格:</label>
						<div class = "col-sm-8">
						<input id = "bookPrice" class = "form-control" name = "bookPrice" type="number">
						</div>
						</div>
						
						<div class = "col-sm-6">
						<label for = "num" class = "control-label col-sm-4">数量:</label>
						<div class = "col-sm-8">
						<input id = "num" name = "num" type="number" class = "form-control">
						</div>
						</div>
					</div>
					
					<div class = "form-group">
						<div class = "col-sm-11 col-sm-offset-1">
						<button type = "button" class = "btn btn-success btn-block">添加</button>
						</div>
					</div>
					
				</form>
			</div>
		</div>
		
		<div class="tab-pane fade" id="updateBooks">
		</div>
	</div>
	
	<div id = "updateBooks" class = "tab-content">
	
	</div>
	
	
	<script type="text/javascript" src = "../bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src = "../css&js/js/setup.js"></script>
</body>
</html>