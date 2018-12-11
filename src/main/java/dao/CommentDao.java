package dao;

import entity.Comment;

import java.util.List;

public interface CommentDao {
    //根据书籍id查评论
    public List<Comment> getComment(String bookId);

    //根据评论id删除评论
    public boolean deleteComment(int commentId);

    //增加评论
    public boolean insertComment(Comment comment);

    //修改评论
    public boolean updateComment(Comment comment);
}
