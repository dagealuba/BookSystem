/**
 *
 */
function regist(){
    var user = $("#regist_form").serialize();

    $.ajax({
        type:"post",
        data:user,
        // data_type:"text",
        url:"/BookSystem/RegistServlet",
        success:function (data) {
            // alert(data);
            if (data=="true"){
                window.location.href="/BookSystem/jsp/html/login.html";
            }
            else if (data=="id-error"){
                var text="该id已注册"
                $("#id-error").text(text);
            }

        }
    })
}


$(document).ready(function () {
    $("#regist_btn").click(function () {
        regist();
    })
})