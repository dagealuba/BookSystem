<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ page import = "entity.User" %>
<% String path = request.getContextPath(); %>
<% User user = (User)request.getSession().getAttribute("user");%>
<% String userName = user.getUserName(); %>
<% int userType = user.getUserType(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>booksystem</title>
<!-- jQuery -->
	<script type="text/javascript" src = "../bootstrap-3.3.7/jQuery/jquery.min.js"></script>
<!-- css -->
<link rel = "stylesheet" href = "../bootstrap-3.3.7/css/bootstrap.min.css">
	<link rel = "stylesheet" href = "../css&js/css/main.css">

</head>
<body class = "container-fluid">
	<nav class = "navbar navbar-default navbar-static-top" role = "navigation">
		<div class = "container-fluid">
		<div class = "navbar-header" style = "margin-left:2%">
			<a class = "navbar-brand" href = "findbooks.jsp" target="iframe">BookSystem</a>
		</div>

		<div>
			<ul class = "nav navbar-nav">
				<li><a href="findbooks.jsp" target="iframe">看书</a></li>
                <li><a href="cart.jsp"     target="iframe">购物车</a></li>

				<li style = "position:absolute;margin-left:77%">
					<a style = "font-size:10px;" class = "dropdown-toggle" data-toggle = "dropdown">
					<span class = "glyphicon glyphicon-user"></span> <%=userName %></a>

					<ul class = "dropdown-menu" style = "font-size:10px;">
						<li><a  data-toggle="modal" data-target="#myMessage">个人信息   <span class = "glyphicon glyphicon-th"></span></a></li>
						<%if (userType == 2){ %>
							<li><a href="setup.jsp" target="iframe">系统管理 <span class = "glyphicon glyphicon-cog"></span></a></li>
						<%} %>
						<li><a href = "../LogoutServlet">注销   <span class = "glyphicon glyphicon-log-out"></span></a></li>

					</ul>
				</li>
			</ul>
		</div>
		</div>
	</nav>

	<iframe id = "iframe" name ="iframe" src = "findbooks.jsp" width="100%" style="border:none" height="590px"></iframe>


    <div class="modal fade" id="myMessage" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">个人信息<small><a><span class="glyphicon glyphicon-pencil"></span></a></small></h4>
				</div>

				<div class="modal-body">

                    <div id="message">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="control-label col-xs-3">${user.getUserName()}</label>
                                <div class="col-xs-9">
                                    <p class="form-control-static">${user.getUserId()}</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-xs-3">Email:</label>
                                <div class="col-xs-9">
                                    <p class="form-control-static">${user.getUserEmail()}</p>
                                </div>

                            </div>
                        </form>
                    </div>

                    <div id="updateMessage" class="hidden">
                        <form class="form-horizontal" role="form" id="update_message">
                            <div class="form-group">
                                <label for="userName" class="col-xs-3">用户名：</label>
                                <div class="col-xs-9">
                                    <input required class="form-control" type="text" id="userName" name="userName" value="${user.getUserName()}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="userId" class="col-xs-3">ID：</label>
                                <div class="col-xs-9">
                                    <input required class="form-control" disabled class="form-control" type="text" id="userId" name="userId" value="${user.getUserId()}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3" for="userPassword">密码:</label>
                                <div class="col-xs-9">
                                    <input required class="form-control" type="password" id="userPassword" name="userPassowrd" placeholder="输入新密码" value="${user.getUserPassword()}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3" for="secPassword">确认密码:</label>
                                <div class="col-xs-9">
                                    <input required class="form-control" type="password" id="secPassword" placeholder="再次确认密码">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="userEmail" class="col-xs-3">邮箱：</label>
                                <div class="col-xs-9">
                                    <input required class="form-control" type="email" id="userEmail" name="userEmail" value="${user.getUserEmail()}">
                                </div>
                            </div>

                        </form>
                    </div>

                    <%--<div id="check_password" class="hidden">--%>
                        <%--<form class="form-horizontal" role="form" id="checkPassword">--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="password" class="control-label col-xs-3">密码:</label>--%>
                                <%--<div class="col-xs-6">--%>
                                    <%--<input type="password" id="password" placeholder="输入你的密码">--%>
                                <%--</div>--%>
                                <%--<button id="back" class="btn btn-danger">返回</button>--%>
                                <%--<button id="sure_check" class="btn btn-success">确认</button>--%>
                            <%--</div>--%>

                           <%----%>
                        <%--</form>--%>
                    <%--</div>--%>
				</div>

				<div class="modal-footer hidden">
                    <button id="return_btn" type="button" class="btn btn-danger">取消</button>
                    <button id="sure_btn" type="button" class="btn btn-success">确认更改</button>
				</div>

			</div>

		</div>
	</div>

	<!-- js -->
	<script type="text/javascript" src = "../bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src = "../css&js/js/main.js"></script>
</body>
</html>