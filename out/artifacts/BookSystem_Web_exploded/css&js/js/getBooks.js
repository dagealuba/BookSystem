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

$(document).ready(function() {
	$("#find-books").click(function() {
		var bookName = $("#bookName").val();
		var bookAuthor = $("#bookAuthor").val();
		var params = $("form").serialize();
		
		$("#error").empty("div");
		$("tbody").empty();
		$("#table-foot").empty();

		xmlReq = getXMLHttpRequest();
		
		if (xmlReq != null){
			xmlReq.open("post", "http://localhost:8080/BookSystem/FindBooksServlet",true);
			xmlReq.onreadystatechange = requestCallBack;
			xmlReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
			xmlReq.send(params);
		}
		
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
		var button = "<td><span class='glyphicon glyphicon-plus'></span><span class='glyphicon glyphicon-shopping-cart'></span></td>"
		node += "<tr><td class='hidden'>"+id+"</td><td>"+name+"</td>";
		node += "<td>"+author+"</td>";
		node += "<td>"+publishName+"</td>";
		node += "<td>"+price+"</td>";
		node += "<td>"+now_num+"</td>"+button+"</tr>";
		
//		alert(node);
		$("tbody").append(node);
	}

}


function requestCallBack() {
	if (xmlReq.readyState == 4) {
        if (xmlReq.status == 200) {
        	var result = xmlReq.responseText;
        	result = eval(result);
        	pages = getAllPages(result);
        	
        	if (pages.length>0){//查到了书，打印结果
        		now_page = 1;
        		printPages(pages,now_page);
        		printButton();
        		

        	}
        	else {
				printNO();
			}
        	
        }
	}
}

//没查到书
function printNO(){
	$("#error").append("<div class='col-md-3 col-md-offset-4'><h2><small>没有找到相关书籍<small></h2></div>")
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
function oldPage(){
	$(".pagination ").children()
}
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
	else if (now_page == pages.length){
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