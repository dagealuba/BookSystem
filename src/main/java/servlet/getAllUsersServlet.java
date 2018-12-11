package servlet;

import entity.User;
import factory.DaoFactory;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class getAllUsersServlet
 */
@WebServlet("/getAllUsersServlet")
public class getAllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated thod stub
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/json;charset=UTF-8");
		List<User> users = new ArrayList<User>();
		users = DaoFactory.getUserDaoImpl().getAllUsers();

		User user = (User)request.getSession().getAttribute("user");

		for (User u: users){
			if (u.getUserId().equals(user.getUserId())){
				users.remove(u);
				break;
			}
		}

		PrintWriter out = response.getWriter();
		if (!users.isEmpty()) {
			JSONArray jsonUsers = JSONArray.fromObject(users);
//			System.out.println(jsonUsers);
			out.write(jsonUsers.toString());
		}
		else {
			out.write("{\"flag\":\"empty\"}");
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
