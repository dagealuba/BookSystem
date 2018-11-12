package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.Book;
import factory.ServiceFactory;
import net.sf.json.JSONArray;


/**
 * Servlet implementation class FindBooksServlet
 */
@WebServlet("/FindBooksServlet")
public class FindBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindBooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");       
		response.setContentType("text/html; charset=utf-8");
		String bookName = request.getParameter("bookName");
		String bookAuthor = request.getParameter("bookAuthor");
		
//		System.out.println("书名:"+bookName+"\n作者:"+bookAuthor+"\n");
		Book book = new Book();
		book.setBookName(bookName);
		book.setBookAuthor(bookAuthor);
		List<Book> books = new ArrayList<Book>();
		
		books = ServiceFactory.getBookServiceImpl().findSomeBooks(book);
//		System.out.println(books.size());
		PrintWriter out = response.getWriter();
		
		JSONArray jsonArray = JSONArray.fromObject(books);
//		System.out.println(jsonArray.toString());
		out.write(jsonArray.toString());
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
