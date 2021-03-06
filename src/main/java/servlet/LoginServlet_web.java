package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;
import entity.User;
import factory.ServiceFactory;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet_web")
public class LoginServlet_web extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet_web() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/json;charset=utf-8");
        String userId = request.getParameter("userId");
        String userPassword = request.getParameter("userPassword");

        PrintWriter out = response.getWriter();

        User user = null;
        user = ServiceFactory.getUserServiveImpl().findUserById(userId);
//		System.out.println(userPassword.equals(user.getUserPassword()));

        if (user != null) {
            if (userPassword.equals(user.getUserPassword())) {
                request.getSession().setAttribute("user",user);
//				System.out.println(userPassword.equals(user.getUserPassword()));

                ServiceFactory.getBorrowServiceImpl().checkFlag(userId);
                out.write("true");
            }
            else {
                out.write("password_error");
            }
        }
        else {
            out.write("id_error");
        }

        out.flush();
        out.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
