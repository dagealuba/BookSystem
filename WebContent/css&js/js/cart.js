$(document).ready(function () {
    getNewBorrow();

    $("#to-cart-page").click(function () {
        getNewBorrow();
    })
    $("#to-history-page").click(function () {
        getHistory();
    })

    $("#to-back").click(function () {
        getBackBorrow();
        $("#borrow").fadeOut(200,function () {
            $("#back").fadeIn();
        })
    })

    $("#to-borrow").click(function () {
        getNewBorrow();
        $("#back").fadeOut(200,function () {
            $("#borrow").fadeIn();
        })
    })

})

function getNewBorrow() {
    $.ajax({
        type:"get",
        url:"/BookSystem/CartServlet1",
        success:function (data) {
           printNewBorrow(data);
        }
    })
}

function getBackBorrow() {
    $.ajax({
        type:"get",
        url:"/BookSystem/CartServlet2",
        success:function (data) {
            printBackBorrow(data);
        }
    })
}

function getHistory() {
    $.ajax({
        type:"get",
        url:"/BookSystem/CartHistoryServlet",
        success:function (data) {
            printHistory(data);
        }
    })
}

function printHistory(data) {
    $("#history-page tbody").empty();
    $("#history-page .table-foot").empty();
    for (var i = 0;i<data.borrows.length;i++){
        var startTime = RiQi(data.borrows[i].startTime);
        var finishTime = RiQi(data.borrows[i].finishTime);
        var flag = "";
        if (data.borrows[i].flag == 2){
            flag = "<td>逾期<span class='glyphicon glyphicon-remove'></span></td>"
        }
        else if (data.borrows[i].flag == 4){
            flag = "<td>未逾期<span class='glyphicon glyphicon-ok'></span></td>"
        }
        var node = "<tr><td class='hidden'>"+data.books[i].bookId+"</td>" +
            "<td>"+data.books[i].bookName+"</td>" +
            "<td>"+startTime+"</td>" +
            "<td>"+1+"</td><td>"+finishTime+"</td>"+flag+"</tr>";
        node = $(node);
        node.fadeToggle();
        $("#history-page tbody").prepend(node);
    }

}

function printNewBorrow(data) {
    $("#borrow tbody").empty();
    $("#borrow .table-foot").empty();
    for (var i = 0;i<data.borrows.length;i++){
        var startTime = RiQi(data.borrows[i].startTime);
        var finishTime = RiQi(data.borrows[i].finishTime);
        var node = "<tr><td class='hidden'>"+data.books[i].bookId+"</td>" +
            "<td>"+data.books[i].bookName+"</td>" +
            "<td>"+startTime+"</td>" +
            "<td>"+1+"</td><td>"+finishTime+"</td><td><span class='glyphicon glyphicon-remove'></span></td></tr>";
        node = $(node);
        node.fadeToggle();
        $("#borrow tbody").prepend(node);
    }

    var node = "<button  class='btn btn-success btn-sm' style='margin-left: 92%'>借阅</button>"
    node = $(node);
    node.fadeToggle();
    $("#borrow .table-foot").prepend(node);

    node.click(function () {
        borrowBooks();
    })
}

function printBackBorrow(data) {
    $("#back tbody").empty();
    $("#back .table-foot").empty();
    for (var i = 0;i<data.borrows.length;i++){
        var startTime = RiQi(data.borrows[i].startTime);
        var finishTime = RiQi(data.borrows[i].finishTime);
        var flag = "";
        if (data.borrows[i].flag==3){
            flag = "<span class='glyphicon glyphicon-remove'></span>"
        }
        else {
            flag = "<span class='glyphicon glyphicon-ok'></span>"
        }


        var node = "<tr><td class='hidden bookId'>"+data.books[i].bookId+"</td><td class='hidden borrowId'>"+data.borrows[i].borrowId+"</td>" +
            "<td>"+data.books[i].bookName+"</td>" +
            "<td>"+startTime+"</td>" +
            "<td>"+1+"</td><td>"+finishTime+"</td><td>"+flag+"</td></tr>";
        node = $(node);
        node.fadeToggle();
        $("#back tbody").prepend(node);
        node.click(function () {
            backBook($(this));
        })
    }

    var node = "<button  class='btn btn-success btn-sm' style='margin-left: 92%'>还书</button>"
    node = $(node);
    node.fadeToggle();
    $("#back .table-foot").prepend(node);

    node.click(function () {
        // var ids = "[";
        var tbody = $(this).parent().parent().children("table").children("tbody");

        backAllBooks();
    })
}

function borrowBooks() {
    $.ajax({
        url: "/BookSystem/CartServlet2",
        type:"post",
        success:function (data) {
            if (data == "true"){
                getNewBorrow();
            }
        }
    })
}

function backAllBooks() {
    $.ajax({
        type:"post",
        url:"/BookSystem/CartServlet3",
        // data:ids,
        success:function (data) {
            if (data=="true"){
                getBackBorrow();
            }
        }
    })
}

function backBook(obj) {
    var id = obj.children(".borrowId").text();
    // alert(id);
    if (confirm("确定要归还该书籍吗?")){
        $.ajax({
            type:"get",
            url:"/BookSystem/CartServlet3",
            data:{
                "borrowId":id
            },
            success:function (data) {
                if (data == "true"){
                    obj.fadeOut();
                }

            }
        })
    }
}



function RiQi(sj) {
    var now = new Date(sj);
    var   year=now.getFullYear();
    var   month=now.getMonth()+1;
    var   date=now.getDate();
    return   year+"-"+month+"-"+date;
}