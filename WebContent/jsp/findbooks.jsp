<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "entity.User" %>
<% String path = request.getContextPath(); %>
<% User user = (User)request.getSession().getAttribute("user");%>
<%--<% String userName = user.getUserName(); %>--%>
<%--<% int userType = user.getUserType(); %>--%>
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
    <input class="hidden" id="user-type" value="<%= user.getUserType()%>">
    <input class="hidden" id="user-id" value="<%=user.getUserId()%>">
	<div class = "row">
		<div class = "col-md-1">
			<h4 >找书</h4>
		</div>
		
		<form id="find-books-form" role = "form" class = "form-inline"  style="padding-top:0.25%">
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
				<th class="hidden">书籍Id</th>
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


	<!-- 书籍信息 -->
	<div class="modal fade" id="bookPage" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title">书籍信息</h4>
				</div>
				<div class="modal-body">
					<div style="overflow: scroll;height: 300px" class="container-fluid">
						<div class="row" id="book-message">
							<div class="col-xs-4">
								<img src="../resource/1.jpg" class="img-thumbnail">
							</div>
							<div class="col-xs-7">
								<div class="row">
									<div class="col-xs-3">
										<strong>书名：</strong>
									</div>
									<div class="col-xs-9">
										<span id="book-name"></span>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-3">
										<strong>作者：</strong>
									</div>
									<div class="col-xs-9">
										<span id="book-author"></span>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4">
										<strong>出版社：</strong>
									</div>
									<div class="col-xs-8">
										<span id="publish-name"></span>
									</div>
								</div>


								<div class="row">
									<div class="col-xs-3">
										<strong>价格</strong>
									</div>
									<div class="col-xs-3">
										<span id="price"></span>元
									</div>
								</div>

								<div class="row">
									<div class="col-xs-3">
										<strong>总数：</strong>
									</div>
									<div class="col-xs-2">
										<span id="num"></span>本
									</div>

									<div class="col-xs-3 col-xs-offset-1">
										<strong>现有：</strong>
									</div>
									<div class="col-xs-2">
										<span id="now-num"></span>本
									</div>
								</div>

							</div>
						</div>

						<div class="row">
							<div class="col-xs-3">
								<h4>评论</h4>
							</div>

							<div class="row">
								<div class="col-xs-12" id="comment-box">
								</div>
								<form class="form-horizontal" role="form" id="new-comment">
									<div class="form-group">
										<textarea id="commentText" class="form-control" rows="3" placeholder="说些什么吧" style="width:90%;margin-left: 5%"></textarea>
									</div>

									<div class="col-xs-12">
										<button type="button" class="btn btn-success pull-right" id="submit-comment">发表</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">

				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
<!-- js -->
	<script type="text/javascript" src = "../bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src ="../css&js/js/getBooks.js"></script>
	
</body>
</html>