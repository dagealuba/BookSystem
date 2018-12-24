$(document).ready(function () {
    $("#to_regist").click(function () {
        $("#login-box").slideUp(300,function () {
            $("#regist-box").slideDown();
        })
    })

    $("#to_login").click(function () {
        $("#regist-box").slideUp(300,function () {
            $("#login-box").slideDown();
        })
    })


    layui.use(['form'], function () {
        var form = layui.form;
        var layer = layui.layer;
        //要放在form.on外面，千万不能放在提交步骤中，否则会不触发
        form.verify({
            //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
            notnull: function (value,item) {
                if (value == "" || value === null){
                    var item_name = ""
                    if (item.name === "userId"){
                        item_name = "用户名";
                    }
                    else if (item.name === "userPassword"){
                        item_name = "用户密码";
                    }
                    else if (item.name === "userEmail"){
                        item_name = "用户邮箱";
                    }
                    else if (item.name === "rePassword"){
                        return "请再输入一次密码";
                    }

                    return item_name+"不能为空"
                }
            },
            same_as_another:function (value,item) {
                // var flag = (value == $("#rePassword").val());
                // layer.msg(flag);
                if (value !== $("#password").val()){
                    return "两次输入的密码不一致，清检查";
                }
            },
            is_num:function (value,item) {
                if ()
            }
        });
        $("#rePassword").change(function () {
            if($(this).val()!==$("#password").val()){
                layer.msg("两次输入的密码不一致，请检查",{
                    icon:5,
                    anim:6,
                    time:1000
                },function () {
                    $("#rePassword").addClass("layui-form-danger");
                    $("#rePassword").focus();
                })
            }
            else $("#rePassword").removeClass("layui-form-danger");
        })
        form.on("submit(login-btn)", function (data) {
            // console.log(data.field);
            $.ajax({
                url:"/BookSystem/LoginServlet_web",
                data:data.field,
                type:"post",
                success:function (res) {
                    if (res === "true"){
                        window.location.href="main.jsp"
                    }
                    if (res === "id_error"||res === "password_error"){
                        layer.msg("用户名或密码错误",{
                            icon:5,
                            anim:6
                        })
                    }
                }
            })
        });

        form.on("submit(regist-btn)", function (data) {
            // return false
            $.ajax({
                url:"/BookSystem/RegistServlet",
                type:"post",
                data:data.field,
                success:function (res) {
                    if (res === "true"){
                        window.location.href = "main.jsp";
                    }
                    else if (res === "false"){
                        layer.msg("注册失败,请稍后重试",{
                            icon:5,
                            anim:6,
                            time:600
                        })
                    }
                    else if (res === "id-error"){
                        layer.msg("用户id已存在",{
                            icon:5,
                            anim:6,
                            time:600
                        })
                    }
                }
            })
        });
    });

})

