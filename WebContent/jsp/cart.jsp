<%@ page import="entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<% User user = (User)request.getSession().getAttribute("user");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
                <a id="to-cart-page" href="#cart-page" data-toggle="tab">购物车</a>
            </li>

            <li>
                <a id="to-history-page" href="#history-page" data-toggle="tab">历史纪录</a>
            </li>
        </ul>
    </div>

    <div  class = "tab-content">
        <div id="cart-page"  class="tab-pane fade in active">
            <div class="container-fluid">
                <div class="row">
                    <ul class="nav nav-tabs">
                        <li><a id="to-borrow">借阅</a></li>
                        <li><a id="to-back">还书</a></li>
                    </ul>
                </div>

                <div class="row">
                    <div id="borrow">
                        <table class="table table-hover">
                            <thead>
                                <th class="hidden">书籍Id</th>
                                <th>书名</th>
                                <th>借出日期</th>
                                <th>借书时长/月</th>
                                <th>还书日期</th>
                                <th></th>
                            </thead>

                            <tbody>

                            </tbody>
                        </table>

                        <div class="row table-foot">

                        </div>
                    </div>

                    <div id="back" style="display: none">
                        <table class="table table-hover">
                            <thead>
                            <th class="hidden">书籍Id</th>
                            <th>书名</th>
                            <th>借出日期</th>
                            <th>借书时长</th>
                            <th>还书日期</th>
                            <th></th>
                            </thead>

                            <tbody>

                            </tbody>
                        </table>

                        <div class="row table-foot">

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="history-page" class="tab-pane fade">
            <table class="table table-hover">
                <thead>
                <th class="hidden">书籍Id</th>
                <th>书名</th>
                <th>借出日期</th>
                <th>借书时长</th>
                <th>还书日期</th>
                <th>是否逾期</th>
                </thead>

                <tbody>

                </tbody>
            </table>
        </div>
    </div>


    <!-- js -->
	<script type="text/javascript" src = "../bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../css&js/js/cart.js"></script>
</body>
</html>