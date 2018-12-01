//设置页面

// //active状态切换
// function changeActive(){
// 	$("ul.dropdown-menu").children("li").click(function() {
// 		if (!$(this).hasClass("active")){
// 			$("ul.dropdown-menu").children("li.active").removeClass("active");
// 		}
// 	})
//
// }

function addbooks(){
	var bookName = $("#bookName").val();
	var bookAuthor = $("#bookAuthor").val();
	var publishName = $("#publishName").val();
	var bookPrice = $("#bookPrice").val();
	var num = $("#num").val();
	var now_num = num;

	var book = {
		"bookName":bookName,
		"bookAuthor":bookAuthor,
		"publishName":publishName,
		"bookPrice":bookPrice,
		"now_num":now_num,
		"num":num
	}

	$.ajax({
		type:"post"
	})

}

function getAllUsers(){
	$.ajax({
		type:"get",
		url:"/BookSystem/getAllUsersServlet",
		success:function (data) {
			$("#userSetup tbody").empty();
			$("#error").empty();
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

				node += "<td><span class='glyphicon glyphicon-trash' style='color: red'></span></td></tr>"

				$("#users_table tbody").append(node);


			}
            $(".glyphicon-trash").click(function () {
                deleteUser($(this));
            })
			$(".each_user").click(function () {
				getUserMessage($(this));
            })
        },
		error:function () {
			$("#userSetup tbody").empty();
			$("#error").append("<div class='col-md-3 col-md-offset-4'><h2><small>出现问题请刷新重试<small></h2></div>")
        }

	})
}

function deleteUser(obj) {
	obj = obj.parent().parent();
	var id = { "userId":obj.children(".userId")};
	$.ajax({
		type:"post",
		data:id,
		url: "/BookSystem/DeletUserServlet",
		success:function (data) {
			
        },
		error:function () {
			
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

	$("#add_books").click(function () {
		addbooks();
    })
})