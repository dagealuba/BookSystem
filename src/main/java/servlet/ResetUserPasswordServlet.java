package servlet;

import entity.User;
import factory.DaoFactory;
import factory.ServiceFactory;
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
@WebServlet("/ResetUserPasswordServlet")
public class ResetUserPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetUserPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated thod stub
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "charset=UTF-8");

        String id = request.getParameter("userId");
        User user_u = DaoFactory.getUserDaoImpl().getUserById(id);
        User user = (User) request.getSession().getAttribute("user");
        user = DaoFactory.getUserDaoImpl().getUserById(user.getUserId());

        PrintWriter out = response.getWriter();
        if (user_u.getUserType()>=user.getUserType()){
            out.write("no-power");
        }
        else {
            List<User> users = new ArrayList<User>();
            user_u.setUserPassword("123456");
            users.add(user_u);
            if (ServiceFactory.getUserServiveImpl().update(users)){
                out.write("true");
            }
            else {
                out.write("false");
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
