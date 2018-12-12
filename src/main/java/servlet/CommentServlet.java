package servlet;

import com.alibaba.fastjson.JSON;
import entity.Comment;
import factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
//        System.out.println(request.getParameter("commentId"));
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        String commentText = request.getParameter("commentText");
        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setCommentText(commentText);

        PrintWriter out = response.getWriter();
        Map res = new HashMap();

        if (ServiceFactory.getCommentServiceImpl().updateComment(comment)){
            res.put("flag","true");
            String bookId = ServiceFactory.getCommentServiceImpl().getCommentByCommentId(commentId).getBookId();
            res.put("bookId",bookId);
        }
        else {
            res.put("flag","false");
        }
        String resJson = JSON.toJSONString(res);
        out.write(resJson);
        out.flush();
        out.close();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int commentId = Integer.parseInt(request.getParameter("commentId"));
//        System.out.println(commentId);
        Comment comment = new Comment();
        comment.setCommentId(commentId);

        PrintWriter out = response.getWriter();
        if (ServiceFactory.getCommentServiceImpl().deleteComment(comment)){
            out.write("true");
        }
        else {
            out.write("false");
        }
    }

}
