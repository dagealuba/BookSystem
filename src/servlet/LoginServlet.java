package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.mysql.jdbc.Driver;

import entity.User;
import factory.ServiceFactory;

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		
		PrintWriter out = response.getWriter();
		
		User user = null;
		user = ServiceFactory.getUserServiveImpl().findUserById(userId);
		
		if (user != null) {
			if (userPassword.equals(user.getUserPassword())) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/jsp/main.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
