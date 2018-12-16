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
        String sql = "select * from borrow where userId = ? and flag = 2 or flag = 4 order by startTime asc";

        return getBorrows(userId, borrows, sql);
    }

    @Override
    public List<Borrow> getBorrowsByUserId_notFinish(String userId) {
        List<Borrow> borrows = new ArrayList<Borrow>();
        String sql = "select * from borrow where userId = ? and flag = 0 order by startTime asc";

        return getBorrows(userId, borrows, sql);
    }

    @Override
    public List<Borrow> getShouldBackByUserId(String userId) {
        List<Borrow> borrows = new ArrayList<Borrow>();
        String sql = "select * from borrow where userId = ? and flag = 1 or flag = 3 order by startTime asc";

        return getBorrows(userId, borrows, sql);
    }

    private List<Borrow> getBorrows(String userId, List<Borrow> borrows, String sql) {
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
        String sql = "update borrow set flag = 1 where borrowId = ?";

        Object[] params = {borrow.getBorrowId()};

        return this.executeUpdate(sql,params)>0;
    }

    public boolean updateBorrow(Borrow borrow){
        String sql = "update borrow set flag = ? where borrowId = ?";
        Object[] params = {borrow.getFlag(),borrow.getBorrowId()};

        return this.executeUpdate(sql,params)>0;
    }


    @Override
    public boolean backBook(Borrow borrow) {
//       Borrow borrow = getBorrowsByBorrowId(borrowId);

       String sql;

       if (borrow.getFlag()==3){
           sql = "update borrow set flag = 2 where borrowId = ?";
       }
       else {
           sql = "update borrow set flag = 4 where borrowId = ?";
       }

       Object[] params = {borrow.getBorrowId()};

       System.out.println(params[0]);

        return this.executeUpdate(sql,params) > 0;
    }

    @Override
    public Borrow getBorrowsByBorrowId(int borrowId) {
        String sql = "select * from borrow where borrowId = ?";
        Object[] params = {borrowId};

        Borrow borrow = new Borrow();
        resultSet = this.ExecuteQuery(sql,params);
        try {
            while (resultSet.next()){
//                borrow = new Borrow();
                borrow.setBookId(resultSet.getString("bookId"));
                borrow.setBorrowId(borrowId);
                int flag = resultSet.getInt("flag");
//                System.out.println("flag:"+flag);
                borrow.setFlag(flag);
//                System.out.println(borrow.getFlag());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource();
        }

//        System.out.println(borrowId);
//        System.out.println("borrow.getBorrowId():"+borrow.getBorrowId());
        return borrow;
    }

    @Override
    public boolean addBorrow(Borrow borrow) {
        String sql = "insert into borrow(bookId,userId,startTime,finishTime,flag) values(?,?,now(),date_sub(now(),interval -1 month),0)";

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
