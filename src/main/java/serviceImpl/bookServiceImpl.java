package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import entity.Book;
import factory.DaoFactory;
import service.bookService;

public class bookServiceImpl implements bookService {

	@Override
	public List<Book> findAllBooks() {
		// TODO Auto-generated method stub
		return DaoFactory.getBookDaoImpl().getAll();
	}

	@Override
	public List<Book> findSomeBooks(Book book) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		
		if (book.getBookName()!=null&&!book.getBookName().equals("")) {
			books = DaoFactory.getBookDaoImpl().getBooksByName(book.getBookName());
			if (!books.isEmpty()) {
				if (book.getBookAuthor()!=null&&!book.getBookAuthor().equals("")) {
					List<Book> books2 = new ArrayList<Book>();
					for (Book b: books) {
						if (b.getBookAuthor().indexOf(book.getBookAuthor()) != -1) {
							books2.add(b);
						}
					}
					return books2;
				}
			}
		}
		else if (book.getBookAuthor()!=null&&!book.getBookAuthor().equals("")) {
			books = DaoFactory.getBookDaoImpl().getBookByAuthor(book.getBookAuthor());
		}
//		System.out.println(books.size());
		return books;
	}
	
	@Override
	public Book findBookById(String bookId) {
		return DaoFactory.getBookDaoImpl().getBookById(bookId);
	}

	@Override
	public boolean newBook(List<Book> books) {
		// TODO Auto-generated method stub
		int i = 0;
		int n = books.size();
		for (Book book: books) {
			if (DaoFactory.getBookDaoImpl().getBookById(book.getBookId()) == null) {
				if (DaoFactory.getBookDaoImpl().add(book)) {
					i++;
				}
			}
			else {
				book.setNow_num(findBookById(book.getBookId()).getNow_num()+book.getNow_num());
				book.setNum(findBookById(book.getBookId()).getNum()+book.getNum());
				if (DaoFactory.getBookDaoImpl().add(book)) {
					i++;
				}
			}
		}
		
		return i == n;
	}

	@Override
	public boolean update(List<Book> books) {
		// TODO Auto-generated method stub
		int i = 0;
		int n = books.size();
		for (Book book:books) {
			if (DaoFactory.getBookDaoImpl().getBookById(book.getBookId())!=null) {
				if (DaoFactory.getBookDaoImpl().update(book)) {
					i++;
				}
			}
		}
		return i == n;
	}

	@Override
	public boolean delete(List<Book> books) {
		// TODO Auto-generated method stub
		int i = 0;
		int n = books.size();
		for (Book book:books) {
			if (DaoFactory.getBookDaoImpl().getBookById(book.getBookId()) != null) {
				if (DaoFactory.getBookDaoImpl().delete(book)) {
					i++;
				}
			}
		}
		
		return i==n;
	}

}
