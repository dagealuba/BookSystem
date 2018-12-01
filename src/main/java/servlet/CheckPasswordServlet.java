package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import entity.User;
import factory.ServiceFactory;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/CheckPasswordServlet")
public class CheckPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setContentType("charset=UTF-8");

        String userPassword = request.getParameter("password");
        User user = (User)request.getSession().getAttribute("user");
        String userId = user.getUserId();

        PrintWriter out = response.getWriter();

        User u = factory.ServiceFactory.getUserServiveImpl().findUserById(userId);
        System.out.println(u.getUserPassword().equals(userPassword));
        if (u.getUserPassword().equals(userPassword)){
            out.write("true");
        }
        else {
            out.write("false");
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
