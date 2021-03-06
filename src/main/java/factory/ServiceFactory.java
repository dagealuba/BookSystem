package factory;

import serviceImpl.BorrowServiceImpl;
import serviceImpl.CommentServiceImpl;
import serviceImpl.bookServiceImpl;
import serviceImpl.userServiceImpl;

public class ServiceFactory {
	public static userServiceImpl getUserServiveImpl() {
		return new userServiceImpl();
	}
	
	public static bookServiceImpl getBookServiceImpl() {
		return new bookServiceImpl();
	}

	public static CommentServiceImpl getCommentServiceImpl(){
		return new CommentServiceImpl();
	}

	public static BorrowServiceImpl getBorrowServiceImpl(){
		return new BorrowServiceImpl();
	}
}
