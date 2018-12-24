//设置页面
var searchUsers=null;
var searchBooks=null;


function addbooks(){
	var bookName = $("#bookName").val();
	var bookAuthor = $("#bookAuthor").val();
	var publishName = $("#publishName").val();
	var bookPrice = $("#bookPrice").val();
	var num = $("#num").val();
	// var now_num = num;

	var book = {
		"bookName":bookName,
		"bookAuthor":bookAuthor,
		"publishName":publishName,
		"bookPrice":bookPrice,
		// "now_num":now_num,
		"num":num
	};

	// alert(book.bookName);

	$.ajax({
		type:"post",
        data:book,
        url:"/BookSystem/AddBookServlet",
        success:function (data) {
            // alert(data);
            var node=" <div class=\"alert alert-success\">\n" +
                "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                "                            &times;\n" +
                "                        </a>\n" +
                "                        <strong>成功!</strong>添加书籍成功\n" +
                "                    </div>"

            node = $(node);
            node.fadeToggle();

            $("#book_setup").prepend(node);
        },
        error:function (data) {

        }
	})

}

function getAllUsers(){
	var users=null;
	$.ajax({
		type:"get",
		url:"/BookSystem/getAllUsersServlet",
		success:function (data) {
			if (data.flag!="empty"){
                $("#userSetup tbody").empty();
                for (var i=0;i<data.length;++i){
                    var userName = data[i].userName;
                    var id = data[i].userId;
                    var email = data[i].userEmail;
                    var type = data[i].userType;

                    var node = "";
                    node += "<tr class='each_user'><td class='userId'>"+id+"</td>";
                    node += "<td>"+userName+"</td>";
                    node += "<td>"+email+"</td>";
                    if (type === 1){
                        node += "<td>用户</td>";
                    }
                    else node += "<td>管理员</td>";

                    node += "<td class='col-xs-3'><span class='glyphicon glyphicon-refresh'></span><span class='glyphicon glyphicon-trash' style='color: red;padding-left: 15px'></span></td></tr>"
                    node = $(node);
                    node.fadeToggle();
                    $("#users_table tbody").append(node);


                }
                $(".glyphicon-trash").click(function () {
                    if (confirm("确定要删除该用户吗？")){
                        deleteUser($(this));
                    }
                });
                $(".glyphicon-refresh").click(function () {
					if (confirm("确定要重置该用户密码？")){
                        resetUserPassword($(this));
                    }
                });
                $(".glyphicon").hover(function () {
					$(this).css("font-size","120%");
                },function () {
					$(this).css("font-size","100%");
                })
                $(".each_user").click(function () {
                    getUserMessage($(this));
                })
			}
        },
		error:function () {
			$("#userSetup tbody").empty();
			$("#error").append("<div class='col-md-3 col-md-offset-4'><h2><small>出现问题请刷新重试<small></h2></div>")
        }

	})
}

function deleteUser(obj) {
	obj = obj.parent().parent();
	var id = { "userId":obj.children(".userId").text()};
	$.ajax({
		type:"get",
		data:id,
		url: "/BookSystem/DeleteUserServlet",
		success:function (data) {
			// alert(typeof data);
			if (data=="no-power"){
                var node=" <div class=\"alert alert-danger\">\n" +
                    "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                    "                            &times;\n" +
                    "                        </a>\n" +
                    "                        <strong>错误!</strong>没有权限！！\n" +
                    "                    </div>"
                node=$(node);
                node.fadeToggle();
                $("#userSetup").prepend(node);
			}
			else if (data=="false"){
                var node=" <div class=\"alert alert-danger\">\n" +
                    "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                    "                            &times;\n" +
                    "                        </a>\n" +
                    "                        <strong>错误!</strong>删除失败,请稍后重试\n" +
                    "                    </div>"
                node=$(node);
                node.fadeToggle();
                $("#userSetup").prepend(node);
			}
			else if (data=="true"){
				obj.fadeOut();
			}


        },
		error:function () {
			
        }
	})
}

function resetUserPassword(obj) {
    obj = obj.parent().parent();
    var id = { "userId":obj.children(".userId").text()};
    
    $.ajax({
		type:"get",
		data:id,
		url:"/BookSystem/ResetUserPasswordServlet",
		success:function (data) {
            if (data=="no-power"){
                var node=" <div class=\"alert alert-danger\">\n" +
                    "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                    "                            &times;\n" +
                    "                        </a>\n" +
                    "                        <strong>错误!</strong>没有权限！！\n" +
                    "                    </div>"
                node=$(node);
                node.fadeToggle();
                $("#userSetup").prepend(node);
            }
            else if (data=="false"){
                var node=" <div class=\"alert alert-danger\">\n" +
                    "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                    "                            &times;\n" +
                    "                        </a>\n" +
                    "                        <strong>错误!</strong>重置失败,请稍后重试\n" +
                    "                    </div>"
                node=$(node);
                node.fadeToggle();
                $("#userSetup").prepend(node);
            }
            else if (data=="true"){
                var node=" <div class=\"alert alert-success\">\n" +
                    "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                    "                            &times;\n" +
                    "                        </a>\n" +
                    "                        重置成功\n" +
                    "                    </div>"
                node=$(node);
                node.fadeToggle();
                $("#userSetup").prepend(node);
            }
        }
	})
}

function getSomeUsers() {
	var message = $("#searchUserForm").serialize();
	// alert(message);
	$.ajax({
		type:"get",
		data:message,
		url:"/BookSystem/SearchUserServlet",
		success:function (data) {
            if (data.flag!="empty"){
                $("#userSetup tbody").empty();
                for (var i=0;i<data.length;++i){
                    var userName = data[i].userName;
                    var id = data[i].userId;
                    var email = data[i].userEmail;
                    var type = data[i].userType;

                    var node = "";
                    node += "<tr class='each_user'><td class='userId'>"+id+"</td>";
                    node += "<td>"+userName+"</td>";
                    node += "<td>"+email+"</td>";
                    if (type === 1){
                        node += "<td>用户</td>";
                    }
                    else node += "<td>管理员</td>";

                    node += "<td class='col-xs-3'><span class='glyphicon glyphicon-refresh'></span><span class='glyphicon glyphicon-trash' style='color: red;padding-left: 15px'></span></td></tr>"
                    node = $(node);
                    node.fadeToggle();
                    $("#users_table tbody").append(node);


                }
                $(".glyphicon-trash").click(function () {
                    if (confirm("确定要删除该用户吗？")){
                        deleteUser($(this));
                    }
                });
                $(".glyphicon-refresh").click(function () {
                    if (confirm("确定要重置该用户密码？")){
                        resetUserPassword($(this));
                    }
                });
                $(".glyphicon").hover(function () {
                    $(this).css("font-size","120%");
                },function () {
                    $(this).css("font-size","100%");
                })
                $(".each_user").click(function () {
                    getUserMessage($(this));
                })
            }
			else {
                var node=" <div class=\"alert alert-warning\">\n" +
                    "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                    "                            &times;\n" +
                    "                        </a>\n" +
                    "                        没有找到相关用户！！\n" +
                    "                    </div>"
                node=$(node);
                node.fadeToggle();
                $("#userSetup").prepend(node);
			}
        },
		error:function () {
            var node=" <div class=\"alert alert-danger\">\n" +
                "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                "                            &times;\n" +
                "                        </a>\n" +
                "                        <strong>错误!</strong>查询失败,请稍候重试！！\n" +
                "                    </div>";
            node=$(node);
            node.fadeToggle();
            $("#userSetup").prepend(node);
        }
    })
}

function getUserMessage(obj){
}

function getAllBooks(){

}

$(document).ready(function() {
	// changeActive();
	getAllUsers();
	$("a[data-toggle='tab']").on("show.bs.tab",function (e) {
		var activeTab = $(e.target).text();
		if (activeTab==="用户管理"){
			getAllUsers();
		}
		else if (activeTab==="书籍管理"){
			getAllBooks();
		}
    })

	$("#searchUsers").click(function () {
		getSomeUsers();
    })

	$("#add_books").click(function () {
		addbooks();
    })
})