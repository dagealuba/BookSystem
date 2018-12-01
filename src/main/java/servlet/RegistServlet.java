package servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import entity.User;
import factory.ServiceFactory;



/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
		
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String userEmail = request.getParameter("userEmail");
		String userId = request.getParameter("userId");
		int userType = 1;
		System.out.println("开始注册");
		User user = new User(userId, userName, userPassword, userEmail, userType);
		List<User> users = new ArrayList<User>();
		users.add(user);
		if (ServiceFactory.getUserServiveImpl().findUserById(userId) == null) {
			System.out.println("无重复用户id");
			if (ServiceFactory.getUserServiveImpl().insert(users)) {
				System.out.println("注册成功");
				request.getRequestDispatcher("/LoginServlet").forward(request, response);
			}

		}
		else response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
