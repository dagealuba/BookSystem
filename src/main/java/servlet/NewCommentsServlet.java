package servlet;

import entity.Comment;
import factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/NewCommentsServlet")
public class NewCommentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        response.setContentType("");

        String userId = request.getParameter("userId");
        String bookId = request.getParameter("bookId");
        String commentText = request.getParameter("commentText");

        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setCommentText(commentText);
        comment.setBookId(bookId);

        PrintWriter out = response.getWriter();
        if (ServiceFactory.getCommentServiceImpl().newComment(comment)){
            out.write("true");
        }
        else {
            out.write("false");
        }
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
