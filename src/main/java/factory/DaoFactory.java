package factory;

import daoImpl.BookDaoImpl;
import daoImpl.BorrowDaoImpl;
import daoImpl.CommentDaoImpl;
import daoImpl.UserDaoImpl;

public class DaoFactory {
	public static UserDaoImpl getUserDaoImpl() {
		return new UserDaoImpl();
	}
	
	public static BookDaoImpl getBookDaoImpl() {
		return new BookDaoImpl();
	}

	public static CommentDaoImpl getCommentDaoImpl(){
		return new CommentDaoImpl();
	}

	public static BorrowDaoImpl getBorrowDaoImpl(){
		return new BorrowDaoImpl();
	}

}
