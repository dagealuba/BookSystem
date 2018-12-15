package daoImpl;

import dao.BorrowDao;
import dao.baseDao;
import entity.Borrow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDaoImpl extends baseDao implements BorrowDao {
    private ResultSet resultSet = null;
    private Connection connection = null;
    private PreparedStatement pStatement = null;

    @Override
    public List<Borrow> getBorrowsByUserId(String userId) {
        List<Borrow> borrows = new ArrayList<Borrow>();
        String sql = "select * from borrow where userId = ? , flag > 0 order by startTime asc";

        Object[] params = {userId};
        resultSet = this.ExecuteQuery(sql,params);

        try{
            while (resultSet.next()){
                Borrow borrow = new Borrow();
                int borrowId = resultSet.getInt("borrowId");
                String bookId = resultSet.getString("bookId");
                Date startTime = resultSet.getDate("startTime");
                Date finishTime = resultSet.getDate("finishTime");
                int flag = resultSet.getInt("flag");
                borrow.setBookId(bookId);
                borrow.setBorrowId(borrowId);
                borrow.setFinishTime(finishTime);
                borrow.setFlag(flag);
                borrow.setUserId(userId);
                borrow.setStartTime(startTime);

                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource();
        }

        return borrows;
    }

    @Override
    public List<Borrow> getBorrowsByUserId_notFinish(String userId) {
        List<Borrow> borrows = new ArrayList<Borrow>();
        String sql = "select * from borrow where userId = ? , flag = 0 order by startTime asc";

        Object[] params = {userId};
        resultSet = this.ExecuteQuery(sql,params);

        try{
            while (resultSet.next()){
                Borrow borrow = new Borrow();
                int borrowId = resultSet.getInt("borrowId");
                String bookId = resultSet.getString("bookId");
                Date startTime = resultSet.getDate("startTime");
                Date finishTime = resultSet.getDate("finishTime");
                int flag = resultSet.getInt("flag");
                borrow.setBookId(bookId);
                borrow.setBorrowId(borrowId);
                borrow.setFinishTime(finishTime);
                borrow.setFlag(flag);
                borrow.setUserId(userId);
                borrow.setStartTime(startTime);

                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource();
        }

        return borrows;
    }

    @Override
    public boolean updateBorrows(Borrow borrow) {
//        String sql = "update borrow set borrow"
        return false;
    }

    @Override
    public boolean addBorrow(Borrow borrow) {
        String sql = "insert into borrow(bookId,userId,startTime,flag) values(?,?,now(),0)";

        Object[] params = {borrow.getBookId(),borrow.getUserId()};

        return this.executeUpdate(sql,params) > 0;
    }

    @Override
    public boolean deleteBorrowById(int borrowId) {
        String sql = "delete from borrow where borrowId = ?";

        Object[] params = {borrowId};

        return this.executeUpdate(sql,params) > 0;
    }
}
