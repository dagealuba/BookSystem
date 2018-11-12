package dao;

import java.util.List;
import entity.Book;;

public interface BookDao {
	//查询所有书
	public List<Book> getAll();
	
	//根据id查书
	public Book getBookById(String bookId);
	
	//根据作者找书
	public List<Book> getBookByAuthor(String author);
	
	//根据部分书名查书
	public List<Book> getBooksByName(String bookName);
	
	//增
	public boolean add(Book book);
	
	//删
	public boolean delete(Book book);
	
	//改
	public boolean update(Book book);
	
}
