/**
 * 
 */
function login(){
    var user = $("#login_form").serialize();

    $.ajax({
        type:"post",
        data:user,
        // data_type:"text",
        url:"/BookSystem/LoginServlet",
        success:function (data) {
            // alert(data);
            if (data=="true"){
                window.parent.location.href="/BookSystem/jsp/main.jsp";
            }
            else if (data=="password_error"){
                var text="密码错误"
                $("#password-error").text(text);
            }
            else if (data=="id_error"){
                var text="无该用户"
                $("#id-error").text(text);
            }

        }
    })
}


$(document).ready(function () {
    $("#login_btn").click(function () {
        login();
    })
})