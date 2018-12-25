package servlet;

import com.alibaba.fastjson.JSON;
import entity.Book;
import entity.Borrow;
import entity.User;
import factory.DaoFactory;
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

@WebServlet("/CartServlet4")
public class CartServlet4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("utf-8");

        User user = (User) request.getSession().getAttribute("user");

        String id = request.getParameter("borrowId");
        int borrowId = Integer.parseInt(id);
        PrintWriter out = response.getWriter();
        if (ServiceFactory.getBorrowServiceImpl().deleteCart(borrowId)){
            out.write("true");
        }
        else {
            out.write("false");
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("utf-8");

        User user = (User) request.getSession().getAttribute("user");

        String id = request.getParameter("borrowId");
        int borrowId = Integer.parseInt(id);

        PrintWriter out = response.getWriter();
        if (ServiceFactory.getBorrowServiceImpl().borrowBook(borrowId)){
            out.write("true");
        }
        else {
            out.write("false");
        }
    }
}
