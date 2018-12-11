package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import dao.UserDao;
import dao.baseDao;
import entity.User;

public class UserDaoImpl extends baseDao implements UserDao {
	private ResultSet resultSet = null;
	private Connection connection = null;
	private PreparedStatement pStatement = null;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		String sql = "select * from users";
		
		Object[] params = {};
		resultSet = this.ExecuteQuery(sql, params);
		
		try {
			while(resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getString("userId"));
				user.setUserName(resultSet.getString("userName"));
				user.setUserPassword(resultSet.getString("userPassword"));
				user.setUserEmail(resultSet.getString("userEmail"));
				user.setUserType(resultSet.getInt("userType"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			this.closeResource();
		}
		
		return users;
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		String sql = "select * from users where userId = ?";
		
		Object[] params = {userId};
		resultSet = this.ExecuteQuery(sql, params);
		User user = null;
		try {
			while(resultSet.next()) {
				user = new User();
				user.setUserId(userId);
				user.setUserName(resultSet.getString("userName"));
				user.setUserPassword(resultSet.getString("userPassword"));
				user.setUserEmail(resultSet.getString("userEmail"));
				user.setUserType(resultSet.getInt("userType"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeResource();
		}
		
		return user;
	}

	@Override
	public  List<User> getUserByName(String userName){
		List<User> users = new ArrayList<User>();
		String sql = "select * from users where userName like ?";

		userName = "%"+userName+"%";
		Object[] params = {userName};
//        System.out.println(userName);
		resultSet = this.ExecuteQuery(sql,params);

		try {
			while(resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getString("userId"));
				user.setUserName(resultSet.getString("userName"));
				user.setUserPassword(resultSet.getString("userPassword"));
				user.setUserEmail(resultSet.getString("userEmail"));
				user.setUserType(resultSet.getInt("userType"));

//				System.out.println(user.getUserId());
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeResource();
		}

		return users;
	}
	@Override
	public boolean add(User user) {
		// TODO Auto-generated method stub

		String sql = "insert into users values(?,?,?,?,1)";
			
		String userId = user.getUserId();
		String userName = user.getUserName();
		String userEmail = user.getUserEmail();
		String userPassword = user.getUserPassword();
		
		System.out.println(userName);
		Object[] params = {userName,userId,userPassword,userEmail};
			
		return this.executeUpdate(sql, params) > 0;
	}

	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		String sql = "delete from users where userId = ?";
			
		String userId = user.getUserId();
			
		Object[] params = {userId};
		
		return this.executeUpdate(sql, params)>0;

	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub	

		String sql = "update users set userName = ?,userPassword = ?,userEmail = ? where userId = ?";
			
		String userName = user.getUserName();
		String userId = user.getUserId();
		String userPassword =user.getUserPassword();
		String userEmail = user.getUserEmail();
			
		Object[] params = {userName,userPassword,userEmail,userId};
		
		return this.executeUpdate(sql, params)>0;

	}

}
