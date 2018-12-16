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
	<link rel = "stylesheet" href = "../css&js/css/setup.css">

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
			<a href="#book_setup" data-toggle="tab">书籍管理
			</a>
		</li>
	</ul>
	</div>
	
	<div  class = "tab-content">
		<div id = "userSetup" class="tab-pane fade in active">
			<div class="row" style="padding-left: 2%">
				<form role="form" class="form-inline" id="searchUserForm">
					<div class="form-group">
						<label for="userName">用户名:</label>
						<input class="form-control" id="userName" name="userName" placeholder="输入要查找的用户名">
					</div>

					<div class="form-group">
						<label for="userId">用户Id:</label>
						<input class="form-control" id="userId" name="userId" placeholder="输入要查找的用户Id">
					</div>

					<div class="form-group">
						<button class="btn btn-success btn-sm" type="button" id="searchUsers">查找</button>
					</div>
				</form>
			</div>
			<table class = "table table-hover" id="users_table">
				<thead>
					<tr>
						<th>Id</th>
						<th>用户名</th>
						<th>邮箱</th>
						<th>用户类型</th>
						<th></th>
					</tr>
				</thead>
				<tbody>

				</tbody>
				<div id="table-foot">
				</div>

			</table>
		</div>

		<div class="tab-pane fade" id="book_setup">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="bookname" class="control-label col-xs-1">书名:</label>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" placeholder="添加的书名" id="bookName" name="bookName">
                    </div>
                </div>

                <div class="form-group">
                    <label for="bookAuthor" class="control-label col-xs-1">作者:</label>
                    <div class="col-xs-2">
                        <input type="text" class="form-control" placeholder="作者" id="bookAuthor" name="bookAuthor">
                    </div>

                    <label for="publishName" class="control-label col-xs-1">出版社:</label>
                    <div class="col-xs-2">
                        <input type="text" class="form-control" placeholder="出版社" id="publishName" name="publishName">
                    </div>
                </div>

                <div class="form-group">
                    <label for="bookPrice" class="control-label col-xs-1">价格:</label>
                    <div class="col-xs-2">
                        <input type="number" class="form-control" placeholder="价格" id="bookPrice" name="bookPrice">
                    </div>

                    <label for="num" class="control-label col-xs-1">数量:</label>
                    <div class="col-xs-2">
                        <input type="number" class="form-control" placeholder="数量" id="num" name="num">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-6">
                        <button type="reset" class="btn btn-warning bookbtn" id="reset">重置</button>
                        <button type="button" class="btn btn-success bookbtn" id="add_books">确定</button>
                    </div>
                </div>
            </form>
		</div>
		
		<%--<div class="tab-pane fade" id="addBooks">--%>
			<%--<div style = "width:60%">--%>
				<%--<form role = "form" class = "form-horizontal">--%>
					<%--<div class = "form-group">--%>
						<%--<label for = "bookName" class = "control-label col-sm-2">书名: </label>--%>
						<%--<div class = "col-sm-10">--%>
						<%--<input class = "form-control" id = "bookName" name = "bookName" type="text" placeholder = "书名">--%>
						<%--</div>--%>
					<%--</div>--%>
					<%----%>
					<%--<div class = "form-group">--%>
						<%--<label for = "bookAuthor" class = "control-label col-sm-2">作者:</label>--%>
						<%--<div class = "col-sm-10">--%>
						<%--<input id = "bookAuthor" class = "form-control" name = "bookAuthor" type = "text" placeholder = "作者">--%>
						<%--</div>--%>
					<%--</div>--%>
					<%----%>
					<%--<div class = "form-group">--%>
						<%--<label for = "publishName" class = "control-label col-sm-2">出版社:</label>--%>
						<%--<div class = "col-sm-10">--%>
						<%--<input type="text" class = "form-control" id = "publishName" name = "publishName" placeholder = "出版社">--%>
						<%--</div>--%>
					<%--</div>--%>
					<%----%>
					<%--<div class = "form-group">--%>
						<%--<div class= "col-sm-6">--%>
						<%--<label for = "bookPrice" class = "control-label col-sm-4">价格:</label>--%>
						<%--<div class = "col-sm-8">--%>
						<%--<input id = "bookPrice" class = "form-control" name = "bookPrice" type="number">--%>
						<%--</div>--%>
						<%--</div>--%>
						<%----%>
						<%--<div class = "col-sm-6">--%>
						<%--<label for = "num" class = "control-label col-sm-4">数量:</label>--%>
						<%--<div class = "col-sm-8">--%>
						<%--<input id = "num" name = "num" type="number" class = "form-control">--%>
						<%--</div>--%>
						<%--</div>--%>
					<%--</div>--%>
					<%----%>
					<%--<div class = "form-group">--%>
						<%--<div class = "col-sm-11 col-sm-offset-1">--%>
						<%--<button type = "button" class = "btn btn-success btn-block" id="add_books">添加</button>--%>
						<%--</div>--%>
					<%--</div>--%>
					<%----%>
				<%--</form>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%----%>
		<%--<div class="tab-pane fade" id="updateBooks">--%>
		<%--</div>--%>
	</div>
	

	
	
	<script type="text/javascript" src = "../bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src = "../css&js/js/setup.js"></script>
</body>
</html>