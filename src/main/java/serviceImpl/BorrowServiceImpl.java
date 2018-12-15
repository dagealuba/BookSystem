package serviceImpl;

import entity.Borrow;
import factory.DaoFactory;
import service.BorrowService;

import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    @Override
    public List<Borrow> getCartByUserId(String userId) {
        return DaoFactory.getBorrowDaoImpl().getBorrowsByUserId_notFinish(userId);
    }

    @Override
    public List<Borrow> getOldCartByUserId(String userId) {
        return DaoFactory.getBorrowDaoImpl().getBorrowsByUserId(userId);
    }

    @Override
    public boolean addToCart(Borrow borrow) {
        return DaoFactory.getBorrowDaoImpl().addBorrow(borrow);
    }

    @Override
    public boolean deleteCart(int borrowId) {
        return DaoFactory.getBorrowDaoImpl().deleteBorrowById(borrowId);
    }
}
