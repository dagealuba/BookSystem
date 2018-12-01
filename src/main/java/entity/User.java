package entity;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private int userType;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String id,String name,String password,String email,int type) {
		userId = id;
		userName = name;
		userPassword = password;
		userEmail = email;
		userType = type;
	}
	public User(String id,String name,String password,String email){
		userId = id;
		userName = name;
		userPassword = password;
		userEmail = email;
	}
	
	public User(String name,String password,String email) {
		userName = name;
		userPassword = password;
		userEmail = email;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public int getUserType() {
		return userType;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public void setUserType(int userType) {
		this.userType = userType;
	}

}
