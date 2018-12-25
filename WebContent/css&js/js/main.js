/**
 * 
 */
//
//$(document).ready(function(){
//	alert($("#iframe"));
//})


//切换模态框内容
function switch_page(){
    if(!$("#message").hasClass("hidden")){
        $("#message").addClass("hidden");
        $("#updateMessage").removeClass("hidden");
        $(".modal-footer").removeClass("hidden");
    }
}

//修改按钮
function pencil_btn(){
    // $("#input-password").modal();
    var password=prompt("输入密码：");
    // var user = {
    //     "userId":$("#userId").val(),
    //     "userPassword":password,
    // }
    if (password!=null){
        $.ajax({
            type:"post",
            data:{
                "password":password
            },
            data_type:"text",
            url:"/BookSystem/CheckPasswordServlet",
            success:function (data){
                if (data=="true"){
                    switch_page();
                }
                else{
                    var node=" <div class=\"alert alert-danger\">\n" +
                        "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                        "                            &times;\n" +
                        "                        </a>\n" +
                        "                        <strong>错误!</strong>密码错误！！\n" +
                        "                    </div>"
                    node=$(node);
                    node.fadeToggle();
                    $(".modal-body").prepend(node);
                }
            },
            error:function(){
                var node=" <div class=\"alert alert-danger\">\n" +
                    "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                    "                            &times;\n" +
                    "                        </a>\n" +
                    "                        <strong>错误!</strong>请求错误请稍后重试！！\n" +
                    "                    </div>"
                node=$(node);
                node.fadeToggle();
                $(".modal-body").prepend(node);
            }
        })
    }

}

//修改用户信息
function update_btn(){
    var userName=$("#userName").val();
    var userPassword=$("#userPassword").val();
    var userEmail=$("#userEmail").val();
    var userId=$("#userId").val();

    var updateMessage = {
        "userId":userId,
        "userName":userName,
        "userPassword":userPassword,
        "userEmail":userEmail
    }

    $.ajax({
        type: "post",
        data: updateMessage,
        url: "/BookSystem/UpdateMessageServlet",
        success:function (data) {
            if (data=="false"){
                var node=" <div class=\"alert alert-danger\">\n" +
                    "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                    "                            &times;\n" +
                    "                        </a>\n" +
                    "                        <strong>错误!</strong>请求错误请稍后重试！！\n" +
                    "                    </div>"
                $(".modal-body").prepend(node);
            }
            else if (data=="true"){
                window.location.href="/BookSystem/LogoutServlet";
            }
        }
    })
}


$(document).ready(function () {
    $(".glyphicon-pencil").hover(function () {
        $(this).css("font-size","120%");
    },function () {
        $(this).css("font-size","100%");
    })

    $("#myMessage").on("shown.bs.modal",function () {
        $(".glyphicon-pencil").click(function () {
            pencil_btn();
        });

        $("#return_btn").click(function () {
            if (!$(".modal-footer").hasClass("hidden")){
                $("#message").removeClass("hidden");
                $("#updateMessage").addClass("hidden");
                $(".modal-footer").addClass("hidden");
            }
        });

        $("#sure_btn").click(function () {
            update_btn();
        });
    })

    $("#myMessage").on("hidden.bs.modal",function () {
        $("#message").removeClass("hidden");
        $("#updateMessage").addClass("hidden");
        $(".modal-footer").addClass("hidden");
    })
})