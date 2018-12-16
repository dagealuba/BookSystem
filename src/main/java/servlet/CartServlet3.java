package servlet;

import com.alibaba.fastjson.JSON;
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
import java.util.List;

@WebServlet("/CartServlet3")
public class CartServlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

//        JSON borrows = request.getParameter("borrowsIds");
        User user = (User) request.getSession().getAttribute("user");

        List<Borrow> borrows = ServiceFactory.getBorrowServiceImpl().getBackByUserId(user.getUserId());

        PrintWriter out = response.getWriter();

        if (ServiceFactory.getBorrowServiceImpl().backBooks(borrows)){
            out.write("true");
        }
        else{
            out.write("false");
        }

        out.flush();
        out.close();
//        if (ServiceFactory.getBorrowServiceImpl())

//        System.out.println(borrows);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String id = request.getParameter("borrowId");
        int borrowId = Integer.parseInt(id);
//        System.out.println(id);
//        System.out.println(borrowId);

        Borrow borrow = DaoFactory.getBorrowDaoImpl().getBorrowsByBorrowId(borrowId);
//        System.out.println(borrow.getBorrowId());
        List<Borrow> borrows = new ArrayList<Borrow>();

        borrows.add(borrow);

        PrintWriter out = response.getWriter();

        if (ServiceFactory.getBorrowServiceImpl().backBooks(borrows)){
            out.write("true");
        }
        else {
            out.write("false");
        }
        out.flush();
        out.close();
    }
}
