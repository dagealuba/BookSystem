package servlet;

import com.alibaba.fastjson.JSON;
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
@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserServlet() {
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

        String userName = request.getParameter("userName");
        String userId = request.getParameter("userId");

        PrintWriter out = response.getWriter();
        String user_search = "";
        List<User> users = new ArrayList<User>();
        User user = null;

        if (userId!=null&&!userId.equals("")){
            if (userName!=null&&!userName.equals("")){
                user = ServiceFactory.getUserServiveImpl().findUserById(userId);
                if (user != null){
                    if (user.getUserName().indexOf(userName)==-1){
                        out.write("{\"flag\":\"empty\"}");
                    }
                    else {
//                    System.out.println("name+id");
                        users.add(user);
                        user_search = JSON.toJSONString(users);
                        out.write(user_search);
                    }
                }
                else {
                    out.write("{\"flag\":\"empty\"}");
                }
            }
            else {
                System.out.println("id");
                user = ServiceFactory.getUserServiveImpl().findUserById(userId);
                if (user!=null){
                    users.add(user);
                    user_search = JSON.toJSONString(users);
                    out.write(user_search);
                }
                else out.write("{\"flag\":\"empty\"}");
            }
        }
        else {
            if (userName!=null&&!userName.equals("")){
                System.out.println("name");
                users = ServiceFactory.getUserServiveImpl().findUserByName(userName);
                if (!users.isEmpty()){
                    user_search = JSON.toJSONString(users);
//                System.out.println(user_search);
                    out.write(user_search);
                }
                else out.write("{\"flag\":\"empty\"}");

            }
            else {
                out.write("{\"flag\":\"empty\"}");
            }
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
