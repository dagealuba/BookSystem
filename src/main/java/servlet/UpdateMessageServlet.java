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
@WebServlet("/UpdateMessageServlet")
public class UpdateMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated thod stub
        request.setCharacterEncoding("UTF-8");

        String userName = request.getParameter("userName");
        String userId = request.getParameter("userId");
        String userPassword = request.getParameter("userPassword");
        String userEmail = request.getParameter("userEmail");
        User user_update = new User(userId,userName,userPassword,userEmail);

        List<User> users = new ArrayList<User>();
        users.add(user_update);
        PrintWriter out = response.getWriter();
        if (factory.ServiceFactory.getUserServiveImpl().update(users)){
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
