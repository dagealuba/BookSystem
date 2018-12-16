package service;

import entity.Borrow;

import java.util.List;

public interface BorrowService {
    //获得某个用户的购物车
    public List<Borrow> getCartByUserId(String userId);

    public List<Borrow> getBackByUserId(String userId);
    //获得某个用户的历史记录
    public List<Borrow> getOldCartByUserId(String userId);

    public boolean getCartByUserId_BookId(String userId,String BookId);
    //添加到购物车
    public boolean addToCart(Borrow borrow);

    //借阅图书
    public boolean borrowBooks(String userId);

    //删除订单记录
    public boolean deleteCart(int borrowId);

    public boolean backBooks(List<Borrow> borrows);

    public void checkFlag(String userId);
}
