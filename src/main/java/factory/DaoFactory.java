package factory;

import daoImpl.BookDaoImpl;
import daoImpl.UserDaoImpl;

public class DaoFactory {
	public static UserDaoImpl getUserDaoImpl() {
		return new UserDaoImpl();
	}
	
	public static BookDaoImpl getBookDaoImpl() {
		return new BookDaoImpl();
	}
}
