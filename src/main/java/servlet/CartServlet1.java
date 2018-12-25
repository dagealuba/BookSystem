package servlet;

import com.alibaba.fastjson.JSON;
import entity.Book;
import entity.Borrow;
import entity.User;
import factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/CartServlet1")
public class CartServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String bookId = request.getParameter("bookId");
        User user = (User)request.getSession().getAttribute("user");

        PrintWriter out = response.getWriter();
        if (ServiceFactory.getBorrowServiceImpl().getCartByUserId_BookId(user.getUserId(),bookId)){
            if (ServiceFactory.getBookServiceImpl().findBookById(bookId).getNow_num()>0){
                Borrow borrow = new Borrow();
                borrow.setBookId(bookId);
                borrow.setUserId(user.getUserId());
                if (ServiceFactory.getBorrowServiceImpl().addToCart(borrow)){
                    out.write("true");
                }
                else out.write("false");
            }
            else {
                out.write("no-book");
            }
        }
        else out.write("already-borrow");

        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");

        User user = (User) request.getSession().getAttribute("user");
        Map res = new HashMap();
        List<Borrow> borrows = ServiceFactory.getBorrowServiceImpl().getCartByUserId(user.getUserId());
        List<Book> books = new ArrayList<Book>();

        for (Borrow borrow:borrows){
            Book book = ServiceFactory.getBookServiceImpl().findBookById(borrow.getBookId());
            books.add(book);
        }

        res.put("borrows",borrows);
        res.put("books",books);

        PrintWriter out = response.getWriter();

        String restr = JSON.toJSONString(res);
        out.write(restr);
        out.flush();
        out.close();
    }
}
