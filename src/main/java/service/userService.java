package service;

import java.util.List;

import entity.User;

public interface userService {
	/*
	 * 添加新用户
	 * 检查是否已存在该用户
	 * 不存在就插入
	 * 否则返回flase
	 * */
	public boolean insert(List<User> users);
	
	/*
	 * 修改用户信息
	 * */
	public boolean update(List<User> users);
	
	/*
	 * 删除用户
	 * */
	public boolean delete(List<User> users);
	
	
	/*
	 * 根据用户id查找用户
	 * 返回用户的所有信息
	 * */
	public User findUserById(String userId);
	
	
	/*
	 * 获取所有用户信息
	 * */
	public List<User> findAllUsers();
}
