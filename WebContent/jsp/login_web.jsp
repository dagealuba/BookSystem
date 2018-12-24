<%--
  Created by IntelliJ IDEA.
  User: xuan
  Date: 18-12-23
  Time: 下午3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="height: 100%">
<head>
    <title>login</title>

    <!-- jQuery -->
    <script type="text/javascript" src = "../bootstrap-3.3.7/jQuery/jquery.min.js"></script>
    <link rel="stylesheet" href="../layui-v2.4.5/layui/css/layui.css">

    <style>
        div.center{
            width: 100%;
            height: 35%;
            position: relative;
            padding-top: 15%;
        }

        span.center{
            font-size: 170px;
            width: 20%;
            position: relative;
            margin-left: auto;
            margin-right: auto;
            color: #009688;
        }

        body{
            height: 100%;
            position: absolute;
            background-image: url("../resource/background2_web.jpg");
            background-repeat: no-repeat;
            background-size: cover;
        }

        .layui-collapse{
            border: none;
        }
        .layui-input{
            padding-bottom: 3px;
        }

        a{
            color: #a6e1ec;
        }
        a:link{
            color: #a6e1ec;
        }
        a:visited{
            color: #a6e1ec;
        }
        a:hover{
            color: white;
        }
        a:active{
            color: gray;
        }


    </style>
</head>
<body class="layui-container">


<div class="center">
    <div class="layui-row">
        <center>
            <span class="center">笑川书屋</span>
        </center>
    </div>

    <div class="layui-row">
        <div class="layui-col-lg6 layui-col-lg-offset3 layui-collapse">
            <div class="layui-colla-item">
                <button id="button" class="layui-btn layui-btn-radius layui-btn-fluid layui-colla-title">加大力度</button>
                <div  class="layui-colla-content layui-anim layui-anim-fadein">
                    <form id="login-box" class="layui-form" action="/BookSystem/LoginServlet_web" method="post">
                        <div class="layui-form-item">
                            <div class="layui-fluid">
                                <input type="text" name="userId"  lay-verify="notnull" placeholder="请输入你的id" class="layui-input">
                                <input type="password" name="userPassword"  lay-verify="notnull" placeholder="请输入你的密码" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-fluid">
                                <input type="checkbox"  name="remember" lay-skin="switch" lay-text="记住密码|不记住密码" checked class="layui-input">
                                <a id="to_regist" style="position:absolute;margin-left: 37%;margin-top: 9px; font-size: 120%">我还不是狗粉丝,我要加入他们</a>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-fluid">
                                <button type="button" lay-submit lay-filter="login-btn" class="layui-btn layui-btn-fluid layui-btn-radius">登陆</button>
                            </div>
                        </div>
                    </form>

                    <form id="regist-box" class="layui-form" style="display: none">
                        <div class="layui-form-item">
                            <div class="layui-fluid">
                                <input class="layui-input" type="text" name="userId" lay-verify="notnull" placeholder="请输入你的id">
                                <input class="layui-input" type="text" name="userName" lay-verify="notnull" placeholder="请输入你的用户名">
                                <input type="password" name="userPassword"  lay-verify="notnull" placeholder="请输入密码" class="layui-input" id="password">
                                <input type="password" name="rePassword"  lay-verify="notnull|same_as_another" placeholder="确认密码" class="layui-input" id="rePassword">
                                <input type="email" name="userEmail"  lay-verify="notnull|email" placeholder="请输入你的邮箱" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <a id="to_login" style="position:relative;margin-left: 57%;margin-top: 9px; font-size: 113%">我已经是狗粉丝了,点我返回登陆</a>
                        </div>
                        <div class="layui-form-item">
                            <button type="button" lay-submit lay-filter="regist-btn" class="layui-btn layui-btn-radius layui-btn-fluid">注册</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>


<script src="../layui-v2.4.5/layui/layui.all.js"></script>
<script src="../css&js/web_homework/login.js"></script>
</body>
</html>
