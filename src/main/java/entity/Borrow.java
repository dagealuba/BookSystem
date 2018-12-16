package entity;

import java.util.Date;

public class Borrow {
    private int borrowId;//订单id
    private String bookId;//书籍id
    private String userId;//用户id
    private Date startTime;//借出时间
    private Date finishTime;//还书时间
    private int flag;//还书状态 0--未借出 1--借出未到时 2--逾期已还 3--逾期未还 4--已还

    public Borrow(){

    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
