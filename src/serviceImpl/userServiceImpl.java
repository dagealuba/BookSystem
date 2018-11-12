package serviceImpl;

import java.util.List;

import entity.User;
import factory.DaoFactory;
import service.userService;

public class userServiceImpl implements userService {

	@Override
	public boolean insert(List<User> users) {
		// TODO Auto-generated method stub
		int i = 0;
		int n = users.size();
		for (User user:users) {
			if (DaoFactory.getUserDaoImpl().getUserById(user.getUserId()) == null) {
				if (DaoFactory.getUserDaoImpl().add(user)) {
					i++;
				}
			}
		}
		return i == n;
	}

	@Override
	public boolean update(List<User> users) {
		// TODO Auto-generated method stub
		int i = 0;
		int n = users.size();
		for (User user:users) {
			if (DaoFactory.getUserDaoImpl().getUserById(user.getUserId()) != null) {
				if (DaoFactory.getUserDaoImpl().update(user)) {
					i++;
				}
			}
		}
		return i == n;
	}

	@Override
	public boolean delete(List<User> users) {
		// TODO Auto-generated method stub
		int i = 0;
		int n = users.size();
		for (User user:users) {
			if (DaoFactory.getUserDaoImpl().getUserById(user.getUserId()) != null) {
				if (DaoFactory.getUserDaoImpl().delete(user)) {
					i++;
				}
			}
		}
		return i == n;
	}

	@Override
	public User findUserById(String userId) {
		// TODO Auto-generated method stub
		return DaoFactory.getUserDaoImpl().getUserById(userId);
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return DaoFactory.getUserDaoImpl().getAllUsers();
	}

}
