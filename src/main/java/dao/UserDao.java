package dao;


import entity.User;

import java.util.List;

public interface UserDao {
    //查询全部信息
    public List<User> getAllUsers();

    //查询部分信息
    public User getUserById(String userId);

    //增
    public boolean add(User user);

    //删
    public boolean delete(User user);

    //改
    public boolean update(User user);
}
