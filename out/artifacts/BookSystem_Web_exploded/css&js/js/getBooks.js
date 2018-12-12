/**
 * 
 */

//$("#find-books").click(function(){
//	alert("ok");
//})

var pages;
var page_lines = 1;
var xmlReq = null;
var now_page = -1;
var commentsdata = null;

$(document).ready(function() {
	$("#find-books").click(function() {
		var bookName = $("#bookName").val();
		var bookAuthor = $("#bookAuthor").val();
		var params = {
			"bookName":$("#bookName").val(),
			"bookAuthor":$("#bookAuthor").val()
		}
		
		$("#error").empty("div");
		$("tbody").empty();
		$("#table-foot").empty();

		// xmlReq = getXMLHttpRequest();
		//
		// if (xmlReq != null){
		// 	xmlReq.open("get", "http://localhost:8080/BookSystem/FindBooksServlet",true);
		// 	xmlReq.onreadystatechange = requestCallBack;
		// 	xmlReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		// 	xmlReq.send(params);
		// }
		//

		$.ajax({
			type:"get",
			url: "/BookSystem/FindBooksServlet",
			data: params,
			success: function (data) {
				data = eval(data);
				pages = getAllPages(data);

				if (pages.length>0){//查到了书，打印结果
					now_page = 1;
					printPages(pages,now_page);
					printButton();
				}
				else {
					printNO();
				}

			}
		});


	})
	
	$("#page_lines").change(function() {
		window.page_lines = parseInt($(this).val());
		window.now_page = 1;
		
		if (!(pages.length>0)||pages!=undefined){
			pages = rePages();
			printPages(pages, window.now_page);
			printButton();
		}
	})
})
//重新分页
function rePages(){
	var t = new Array();
	for (var i = 0;i < window.pages.length;i++){
		for (var j = 0;j < window.pages[i].length;j++){
			t.push(window.pages[i][j]);
		}
	}
	
	var length = t.length;
	var page_num = Math.ceil(length/page_lines);
	
	var p = new Array(page_num);
	for (var i=0;i<page_num;i++){
		p[i] = [];
		for (var j=i*page_lines;j<min(i*page_lines+page_lines,length );j++){
			p[i].push(t[j]);
		}
	}
	return p;
	
}


//打印结果到网页上
function printPages(pages,i){
	$("#error").empty("div");
	$("tbody").empty();
	$("#table-foot").empty();
	var length = pages[i-1].length;
	for (var j = 0;j < length;j++){
		var node = "";
		var id = pages[i-1][j].bookId;
		var name = pages[i-1][j].bookName;
		var author = pages[i-1][j].bookAuthor;
		var price = pages[i-1][j].bookPrice;
		var now_num = pages[i-1][j].now_num;
		var publishName = pages[i-1][j].publishName;
		var button = "<td><span class='glyphicon glyphicon-plus' data-toggle='tooltip' title='添加置购物车'></span><span class='glyphicon glyphicon-comment' data-toggle='tooltip' title='查看评论'></span></td>"
		node += "<tr><td class='hidden'>"+id+"</td><td>"+name+"</td>";
		node += "<td>"+author+"</td>";
		node += "<td>"+publishName+"</td>";
		node += "<td>"+price+"</td>";
		node += "<td>"+now_num+"</td>"+button+"</tr>";


		node = $(node);
		node.fadeToggle();
//		alert(node);
		$("tbody").append(node);

		//查看评论
		$(".glyphicon-comment").click(function () {
			var id = $(this).parent().parent().children(".hidden").text();
			id = {
				"id":id
			}
			
			$("#bookPage").modal();

			$("#bookPage").on("shown.bs.modal",function () {
				getComments(id);

				$("#submit-comment").click(function () {
				    var comment = {
				        "userId":$("#user-id").val(),
                        "bookId":id.id,
                        "commentText":$("#commentText").val()
                    }
					$.ajax({
						type:"post",
                        data:comment,
                        url:"/BookSystem/NewCommentsServlet",
                        success:function (data) {
                            if (data=="true"){
                                getComments(id);
                            }

                        }
					})
                });

            })
        });

		//添加至购物车
		$(".glyphicon-plus").click(function () {

        });
	}

}

function getComments(id) {
    $.ajax({
        type:"post",
        url:"/BookSystem/FindBooksServlet",
        data:id,
        dataType: "json",
        success:function (data) {
            $("#comment-box").empty();
            // data =
            $("#book-name").html(data.book.bookName);
            $("#book-author").html(data.book.bookAuthor);
            $("#publish-name").html(data.book.publishName);
            $("#num").html(data.book.num);
            $("#now-num").html(data.book.now_num);
            $("#price").html(data.book.bookPrice);

            if (data.comments.length == 0){
                var node = "\n" +
                    "<div class=\"alert alert-dismissable\">\n" +
                    " <span style='color: gray'>暂无评论</span>\n" +
                    "</div>\n";
                node = $(node);
                // node.fadeToggle();
                $("#comment-box").prepend(node);
            }
            else {
                printComments(data);
            }
        }
    });
}

function printComments(data) {
    for (var i=0;i<data.comments.length;i++){
        var time = data.comments[i].time;
        var stime = RiQi(time);
        var node = "<div class='panel panel-default'>" +
            "<div class='panel-heading'>" +
            "<span class='hidden'>"+data.comments[i].commentId+"</span>" +
            "<h5 class='panel-title'>"+data.users[i].userName+"<small class='pull-right'>"+stime+"</small></h5>" +
            "</div>" +
            "<div class='panel-body'>" +
            data.comments[i].commentText +
            "</div>" +
            "</div>";

        node = $(node);
        if ($("#user-id").val()==data.users[i].userId){
            // node.children(".panel-heading").children().children().append("<span style='color: red' class='glyphicon glyphicon-trash'></span>");
            node.children(".panel-heading").children("h5").children("small").append("<span class='glyphicon glyphicon-pencil updateComment'></span>");
        }
        // alert(node.children(".panel-heading").children().children().text());
        if ($("#user-type").val()>=2||$("#user-id").val()==data.users[i].userId){
            node.children(".panel-heading").children("h5").children("small").append("<span style='color: red' class='glyphicon glyphicon-trash deleteComment'></span>");
        }

        // alert()

        node.fadeToggle();
        $("#comment-box").prepend(node);

        node.children(".panel-heading").children().children().children(".glyphicon-trash").click(function () {
            deleteComment($(this));
        });

        node.children(".panel-heading").children().children().children(".glyphicon-pencil").click(function () {
            updateComment($(this));
        });




    }
}

//删除评论
function deleteComment(obj) {
    obj = obj.parent().parent().parent();

    // alert(obj.html());

    var commentId = obj.children(".hidden").text();
    commentId = {
        "commentId":commentId
    }

    // alert(commentId);
    $.ajax({
        type:"get",
        data:commentId,
        url:"/BookSystem/CommentServlet",
        success:function (data) {
            if (data=="true"){
                obj.parent().fadeToggle();
            }
            else {
                var node=" <div class=\"alert alert-danger\">\n" +
                    "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                    "                            &times;\n" +
                    "                        </a>\n" +
                    "                        删除失败！！\n" +
                    "                    </div>"
                node=$(node);
                node.fadeToggle();
                obj.parent().before(node);
            }


        }
    })
}

//修改评论
function updateComment(obj) {
    obj = obj.parent().parent().parent().parent();
    var commenttext = prompt("修改评论内容：");
    commenttext = {
        "commentText":commenttext,
        "commentId":obj.children(".panel-heading").children(".hidden").text()
    }

    $.ajax({
        type:"post",
        data:commenttext,
        url:"/BookSystem/CommentServlet",
        success:function (data) {
            // alert(typeof );
            if(data.flag=="true"){
                var id = {
                    "id":data.bookId
                }
                // alert(data.bookId);
                getComments(id);
            }
            else if (data.flag=="false"){
                var node=" <div class=\"alert alert-danger\">\n" +
                    "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
                    "                            &times;\n" +
                    "                        </a>\n" +
                    "                        删除失败！！\n" +
                    "                    </div>"
                node=$(node);
                node.fadeToggle();
                obj.before(node);
            }

        }
    })


}

//没查到书
function printNO(){
    var node=" <div class=\"alert alert-danger\">\n" +
        "                        <a class=\"close\" data-dismiss=\"alert\" href=\"#\" aria-hidden=\"true\">\n" +
        "                            &times;\n" +
        "                        </a>\n" +
        "                        <strong>错误!</strong>没有找到相关书籍！！\n" +
        "                    </div>"
    node=$(node);
    node.fadeToggle();
    $("#error").prepend(node);
}

//显示分页按钮
function printButton(){
	var ul = $("<ul class='pagination pagination-sm'></ul>");
	var larr = $("<li ><a class='old-page'>&laquo;</a></li>");
	var rarr = $("<li><a class='next-page'>&raquo;</a></li>");
	ul.append(larr);
	for (var i=0;i<pages.length;i++){
		var html = "";
		if (i==0){
			html = "<li><a id = '"+(i+1)+"' class = 'page-num' onclick='gotoPage(this)'>"+(i+1)+"</a></li>";
		}
		else html = "<li><a id = '"+(i+1)+"' class = 'page-num' onclick='gotoPage(this)'>"+(i+1)+"</a></li>";
		var page = $(html);
		ul.append(page);
	}
	ul.append(rarr);
	$("#table-foot").append(ul);
	$(".pagination").children("li").eq(now_page).attr("class", "active");

	canClick();
	
	if ($(".next-page").parent().hasClass("disabled")){
		$(".next-page").unbind("click",nextPage);
	}
	else {
		$(".next-page").click(function() {
			nextPage();
		})
	}
	
	if ($(".old-page").parent().hasClass("disabled")){
		$(".old-page").unbind("click",oldPage);
	}
	else {
		$(".old-page").click(function() {
			oldPage();
		})
	}
}

//上一页
// function oldPage(){
// 	$(".pagination ").children()
// }
function oldPage(){
	now_page--;
	printPages(pages, now_page);
	printButton();
}

//下一页
function nextPage(){
	now_page++;
	printPages(pages, now_page);
	printButton();
}

//点击页码
function gotoPage(obj){
	now_page = parseInt($(obj).attr("id"));
	printPages(pages, now_page);
	printButton();
}

//翻页按钮状态
function canClick(){
	$(".pagination").children().removeClass("disabled");
	now_page = parseInt($(".pagination").children("li.active").children("a").attr("id"));
	if (now_page == 1){
		$(".pagination").children("li").children(".old-page").parent("li").attr("class", "disabled");
	}
	if (now_page == pages.length){
		$(".pagination").children("li").children(".next-page").parent("li").attr("class","disabled");
	}
	
	
}

//将查到的书分页
function getAllPages(result){
	var length = result.length;
	var page_num = Math.ceil(length/page_lines);
	
	var pages = new Array(page_num);
	for (var i=0;i<page_num;i++){
		pages[i] = [];
		for (var j=i*page_lines;j<min(i*page_lines+page_lines,length );j++){
			pages[i].push(result[j]);
		}
	}
	return pages;
}

//返回较小的数
function min(i,j){
	if (i>=j){
		return j;
	}
	else return i;
}

function getXMLHttpRequest(){
	return new XMLHttpRequest;
}

function RiQi(sj) {
    var now = new Date(sj);
    var   year=now.getFullYear();
    var   month=now.getMonth()+1;
    var   date=now.getDate();
    var   hour=now.getHours();
    // alert(now.toTimeString());
    var   minute=now.getMinutes();
    // var   second=now.getSeconds();
    return   year+"-"+month+"-"+date+"   "+hour+":"+minute;
}