package factory;

import daoImpl.BookDaoImpl;
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
}
