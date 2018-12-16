package serviceImpl;

import entity.Book;
import entity.Borrow;
import factory.DaoFactory;
import service.BorrowService;

import java.util.ArrayList;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    @Override
    public List<Borrow> getCartByUserId(String userId) {
        return DaoFactory.getBorrowDaoImpl().getBorrowsByUserId_notFinish(userId);
    }

    @Override
    public List<Borrow> getBackByUserId(String userId) {
        return DaoFactory.getBorrowDaoImpl().getShouldBackByUserId(userId);
    }

    @Override
    public List<Borrow> getOldCartByUserId(String userId) {
        return DaoFactory.getBorrowDaoImpl().getBorrowsByUserId(userId);
    }

    @Override
    public boolean getCartByUserId_BookId(String userId, String bookId) {
        List<Borrow> borrows = new ArrayList<Borrow>();
        borrows = DaoFactory.getBorrowDaoImpl().getBorrowsByUserId(userId);

        for (Borrow borrow:borrows){
            if (borrow.getBookId() == bookId){
                if (borrow.getFlag()!=2||borrow.getFlag()!=4){
                    return false;
                }
            }

        }
        borrows = DaoFactory.getBorrowDaoImpl().getBorrowsByUserId_notFinish(userId);
        for (Borrow borrow:borrows){
            if (borrow.getBookId() ==  bookId){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addToCart(Borrow borrow) {
        return DaoFactory.getBorrowDaoImpl().addBorrow(borrow);
    }

    @Override
    public boolean borrowBooks(String userId) {
        List<Borrow> borrows = getCartByUserId(userId);

        int n = 0;
        for (Borrow borrow:borrows){
            if (DaoFactory.getBorrowDaoImpl().updateBorrows(borrow)){
                Book book = DaoFactory.getBookDaoImpl().getBookById(borrow.getBookId());
                book.setNow_num(book.getNow_num()-1);

                if (DaoFactory.getBookDaoImpl().update(book)){
                    n++;
                }
            }
        }
        return n == borrows.size();
    }

    @Override
    public boolean deleteCart(int borrowId) {
        return DaoFactory.getBorrowDaoImpl().deleteBorrowById(borrowId);
    }

    @Override
    public boolean backBooks(List<Borrow> borrows) {
        int n=0;
        for (Borrow borrow:borrows){
            if (DaoFactory.getBorrowDaoImpl().backBook(borrow)){
                Book book = DaoFactory.getBookDaoImpl().getBookById(borrow.getBookId());
                book.setNow_num(book.getNow_num()+1);
                if (DaoFactory.getBookDaoImpl().update(book)){
                    n++;
                }
            }
        }
//        System.out.println(n);
        return n == borrows.size();
    }
}
