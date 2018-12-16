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

@WebServlet("/CartHistoryServlet")
public class CartHistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");

        User user = (User) request.getSession().getAttribute("user");
        List<Borrow> borrows = ServiceFactory.getBorrowServiceImpl().getOldCartByUserId(user.getUserId());

        List<Book> books = new ArrayList<Book>();
        for (Borrow borrow:borrows){
            Book book = ServiceFactory.getBookServiceImpl().findBookById(borrow.getBookId());

            books.add(book);
        }

        Map res = new HashMap();
        res.put("borrows",borrows);
        res.put("books",books);

        String restr = JSON.toJSONString(res);

        PrintWriter out = response.getWriter();
        out.write(restr);
        out.flush();
        out.close();
    }
}
