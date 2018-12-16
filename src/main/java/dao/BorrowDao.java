package dao;

import entity.Borrow;

import javax.swing.plaf.basic.BasicListUI;
import java.util.List;

public interface BorrowDao {
    //查询订单
    public List<Borrow> getBorrowsByUserId(String userId);//某个用户的所有已完成的订单

    public List<Borrow> getBorrowsByUserId_notFinish(String userId);//查询某个用户所有未完成的订单

    public List<Borrow> getShouldBackByUserId(String userId);

    public Borrow getBorrowsByBorrowId(int borrowId);
    //修改订单
    public boolean updateBorrows(Borrow borrow);

    public boolean backBook(Borrow borrow);


    //增加订单
    public boolean addBorrow(Borrow borrow);

    //删除订单
    public boolean deleteBorrowById(int borrowId);
}
