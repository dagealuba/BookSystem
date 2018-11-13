package factory;

import serviceImpl.bookServiceImpl;
import serviceImpl.userServiceImpl;

public class ServiceFactory {
	public static userServiceImpl getUserServiveImpl() {
		return new userServiceImpl();
	}
	
	public static bookServiceImpl getBookServiceImpl() {
		return new bookServiceImpl();
	}
}
