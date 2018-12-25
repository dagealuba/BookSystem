package serviceImpl;

import entity.Book;
import entity.Borrow;
import factory.DaoFactory;
import service.BorrowService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        System.out.println(userId);
        return DaoFactory.getBorrowDaoImpl().getBorrowsByUserId(userId);
    }


    @Override
    public boolean getCartByUserId_BookId(String userId, String bookId) {
        List<Borrow> borrows = new ArrayList<Borrow>();
        borrows = DaoFactory.getBorrowDaoImpl().getShouldBackByUserId(userId);

        for (Borrow borrow:borrows){
//            System.out.println("borrow.bookId:"+borrow.getBookId());
//            System.out.println("bookID："+bookId);
            if (borrow.getBookId().equals(bookId)){
                return false;
            }

        }
        borrows = DaoFactory.getBorrowDaoImpl().getBorrowsByUserId_notFinish(userId);
        for (Borrow borrow:borrows){
//            System.out.println("borrow.bookId:"+borrow.getBookId());
//            System.out.println("bookID："+bookId);
            if (borrow.getBookId().equals(bookId)){
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
            Book book = DaoFactory.getBookDaoImpl().getBookById(borrow.getBookId());

            if (book.getNow_num()-1<0){
                continue;
            }
            else if (DaoFactory.getBorrowDaoImpl().updateBorrows(borrow)){

                book.setNow_num(book.getNow_num()-1);

                if (DaoFactory.getBookDaoImpl().update(book)){
                    n++;
                }
            }
        }
        return n == borrows.size();
    }

    @Override
    public boolean borrowBook(int borrowId) {
        Borrow borrow = DaoFactory.getBorrowDaoImpl().getBorrowsByBorrowId(borrowId);

        Book book = DaoFactory.getBookDaoImpl().getBookById(borrow.getBookId());

        if (book.getNow_num()-1 < 0){
            return false;
        }
        else if (DaoFactory.getBorrowDaoImpl().updateBorrows(borrow)){
            book.setNow_num(book.getNow_num()-1);
            if (DaoFactory.getBookDaoImpl().update(book)){
                return true;
            }
        }

        return false;
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

    @Override
    public void checkFlag(String userId) {
        List<Borrow> borrows = this.getBackByUserId(userId);

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String now = simpleDateFormat1.format(date);
//        System.out.println(date);
        for (Borrow borrow: borrows){
            String finishTime = simpleDateFormat1.format(borrow.getFinishTime());

            if (finishTime.compareTo(now)<0){
                borrow.setFlag(3);
                DaoFactory.getBorrowDaoImpl().updateBorrow(borrow);
            }
        }
    }
}
