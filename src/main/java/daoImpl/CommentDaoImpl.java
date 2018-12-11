package daoImpl;

import dao.CommentDao;
import dao.baseDao;
import entity.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl extends baseDao implements CommentDao {
    private ResultSet resultSet = null;
    private Connection connection = null;
    private PreparedStatement pStatement = null;

    @Override
    public List<Comment> getComment(String bookId){
        List<Comment> comments = new ArrayList<Comment>();
        String sql = "select * from comment where bookId=? order by time desc";

        Object[] parmas = {bookId};
        resultSet = this.ExecuteQuery(sql,parmas);

        try{
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setBookId(resultSet.getString("bookId"));
                comment.setUserId(resultSet.getString("userId"));
                comment.setCommentId(resultSet.getInt("commentId"));
                comment.setCommentText(resultSet.getString("commentText"));
                comment.setTime(resultSet.getTimestamp("time"));

                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource();
        }


        return comments;
    }

    @Override
    public boolean deleteComment(int commentId){
        String sql = "delete from comment where commentId = ?";
        Object[] params = {commentId};

        return this.executeUpdate(sql,params) > 0;
    }

    @Override
    public boolean insertComment(Comment comment){
        String sql = "inset into comment(bookId,userId,commentText) values(?,?,?)";

        String bookId = comment.getBookId();
        String userId = comment.getUserId();
        String commentText = comment.getCommentText();

        Object[] params = {bookId,userId,commentText};

        return this.executeUpdate(sql,params) > 0;
    }

    @Override
    public  boolean updateComment(Comment comment){
        String sql = "update comment set commentText = ?,time = now() where commentId = ?";

        String commentText = comment.getCommentText();
        int commentId = comment.getCommentId();

        Object[] params = {commentText,commentId};


        return this.executeUpdate(sql,params) > 0;
    }
}
