package servlet;

import entity.Book;
import factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("charset=UTF-8");

        String bookName = request.getParameter("bookName");
        String bookAuthor = request.getParameter("bookAuthor");
        String publishName = request.getParameter("publishName");
        float bookPrice = Float.parseFloat(request.getParameter("bookPrice"));
        int num = Integer.valueOf(request.getParameter("num"));
        String bookId = UUID.randomUUID().toString();

//        System.out.println(bookId);

        Book book = new Book();
        book.setBookId(bookId);
        book.setBookName(bookName);
        book.setBookAuthor(bookAuthor);
        book.setPublishName(publishName);
        book.setNum(num);
        book.setNow_num(num);
        book.setBookPrice(bookPrice);

        List<Book> books = new ArrayList<Book>();
        books.add(book);


        PrintWriter out = response.getWriter();
        if (ServiceFactory.getBookServiceImpl().newBook(books)){
            out.write("success");
        }
        else {
            out.write("error");
        }

        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
