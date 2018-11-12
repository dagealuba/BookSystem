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


</head>
<body class = "container-fluid">
	<nav class = "navbar navbar-default navbar-static-top" role = "navigation">
		<div class = "container-fluid">
		<div class = "navbar-header" style = "margin-left:2%">
			<a class = "navbar-brand" href = "findbooks.jsp" target="iframe">BookSystem</a>
		</div>
		
		<div>
			<ul class = "nav navbar-nav">
				<li><a href = "findbooks.jsp" target="iframe">看书</a></li>
				
				<li style = "position:absolute;margin-left:77%">
					<a style = "font-size:10px;" class = "dropdown-toggle" data-toggle = "dropdown">
					<span class = "glyphicon glyphicon-user"></span> <%=userName %></a>
					
					<ul class = "dropdown-menu" style = "font-size:10px;">
						<li><a href = "">个人信息   <span class = "glyphicon glyphicon-th"></span></a></li>
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
	
	<!-- js -->
	<script type="text/javascript" src = "../bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src = "../css&js/js/main.js"></script>
</body>
</html>