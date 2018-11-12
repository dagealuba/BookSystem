package service;

import java.util.List;

import entity.Book;

public interface bookService {
	/*
	 * 获得所有书籍信息
	 * */
	public List<Book> findAllBooks();
	
	/*
	 * 根据部分信息查找所有符合条件的书
	 * 如id查询，部分书名查询等
	 * */
	public List<Book> findSomeBooks(Book book);
	
	/*
	 * 根据id找书
	 * */
	public Book findBookById(String bookId);
	
	/**
	 * 添加书
	 */
	public boolean newBook(List<Book> books);
	
	/*
	 * 修改已有书本信息
	 * */
	public boolean update(List<Book> books);
	
	/*
	 * 删除书目
	 * */
	public boolean delete(List<Book> books);
	
	
}
