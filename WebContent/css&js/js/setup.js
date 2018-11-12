//设置页面

//active状态切换
function changeActive(){
	$("ul.dropdown-menu").children("li").click(function() {
		if (!$(this).hasClass("active")){
			$("ul.dropdown-menu").children("li.active").removeClass("active");
		}
	})
}

$(document).ready(function() {
	changeActive();
})