package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BookDao;
import dao.baseDao;
import entity.Book;
import serviceImpl.userServiceImpl;

public class BookDaoImpl extends baseDao implements BookDao {
	private ResultSet resultSet = null;
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	
	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		String sql = "select * from books";
		
		Object[] params = {};
		resultSet = this.ExecuteQuery(sql, params);
		
		try {
			while(resultSet.next()) {
				Book book = new Book();
				book.setBookId(resultSet.getString("bookId"));
				book.setBookName(resultSet.getString("bookName"));
				book.setBookPrice(resultSet.getFloat("bookPrice"));
				book.setBookAuthor(resultSet.getString("bookAuthor"));
				book.setPublishName(resultSet.getString("publishName"));
				book.setNow_num(resultSet.getInt("now_num"));
				book.setNum(resultSet.getInt("num"));
				
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeResource();
		}
 		
		return books;
	}

	@Override
	public Book getBookById(String bookId) {
		// TODO Auto-generated method stub
		String sql = "select * from books where bookId = ?";
		
		Object[] params = {bookId};
		resultSet = this.ExecuteQuery(sql, params);
		Book book = null;
		try {
			while(resultSet.next()) {
				book = new Book();
				book.setBookId(resultSet.getString("bookId"));
				book.setBookName(resultSet.getString("bookName"));
				book.setBookAuthor(resultSet.getString("bookAuthor"));
				book.setBookPrice(resultSet.getFloat("bookPrice"));
				book.setPublishName(resultSet.getString("publishName"));
				book.setNow_num(resultSet.getInt("now_num"));
				book.setNum(resultSet.getInt("num"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeResource();
		}
		return book;
	}

	@Override
	public List<Book> getBooksByName(String bookName) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		String sql = "select * from books where bookName like ?";
		
		bookName = "%"+bookName+"%";
		Object[] params = {bookName};
		resultSet = this.ExecuteQuery(sql, params);
		
		try {
			while(resultSet.next()) {
				Book book = new Book();
				book.setBookId(resultSet.getString("bookId"));
				book.setBookName(resultSet.getString("bookName"));
				book.setBookAuthor(resultSet.getString("bookAuthor"));
				book.setBookPrice(resultSet.getFloat("bookPrice"));
				book.setPublishName(resultSet.getString("publishName"));
				book.setNow_num(resultSet.getInt("now_num"));
				book.setNum(resultSet.getInt("num"));
				
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeResource();
		}
		
		return books;
	}

	@Override
	public boolean add(Book book) {
		// TODO Auto-generated method stub
		String sql = "insert into books values(?,?,?,?,?,?,?)";
		
		String bookId = book.getBookId();
		String bookName = book.getBookName();
		float bookPrice = book.getBookPrice();
		String publishName = book.getPublishName();
		String bookAuthor = book.getBookAuthor();
		int now_num = book.getNow_num();
		int num = book.getNum();
		
		Object[] params = {bookId,bookName,bookAuthor,bookPrice,publishName,now_num,num};
		
		return this.executeUpdate(sql, params) > 0;
	}

	@Override
	public boolean delete(Book book) {
		// TODO Auto-generated method stub
		String sql = "delete from books where bookId = ?";
		String bookId = book.getBookId();
		
		Object[] params = {bookId};
		
		return this.executeUpdate(sql, params) > 0;
	}

	@Override
	public boolean update(Book book) {
		// TODO Auto-generated method stub
		String sql = "update books set bookName = ?,bookAuthor = ?,bookPrice = ?,publishName = ?,now_num = ?,num = ? where bookId = ?";
		
		String bookId = book.getBookId();
		String bookName = book.getBookName();
		float bookPrice = book.getBookPrice();
		String publishName = book.getPublishName();
		String bookAuthor = book.getBookAuthor();
		int now_num = book.getNow_num();
		int num = book.getNum();
		
		Object[] params = {bookName,bookAuthor,bookPrice,publishName,now_num,num,bookId};
		return this.executeUpdate(sql, params) > 0;
	}

	@Override
	public List<Book> getBookByAuthor(String author) {
		// TODO Auto-generated method stub
		String sql = "select * from books where bookAuthor like ?";
		List<Book> books = new ArrayList<Book>();
		
		author = "%"+author+"%";
		
		Object[] params = {author};
		resultSet = this.ExecuteQuery(sql, params);
		
		try {
			while(resultSet.next()) {
				Book book = new Book();
				book.setBookAuthor(resultSet.getString("bookAuthor"));
				book.setBookId(resultSet.getString("bookId"));
				book.setBookName(resultSet.getString("bookName"));
				book.setBookPrice(resultSet.getFloat("bookPrice"));
				book.setPublishName(resultSet.getString("publishName"));
				book.setNow_num(resultSet.getInt("now_num"));
				book.setNum(resultSet.getInt("num"));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeResource();
		}
		
		
		return books;
	}

}
