package service;

import entity.Comment;

import java.util.List;

public interface CommentService {
    //获得某书籍的评论
    public List<Comment> getCommentsByBookId(String bookId);

    //发表评论
    public boolean newComment(Comment comment);

    //修改评论
    public boolean updateComment(Comment comment);

    //删除评论
    public boolean deleteComment(Comment comment);

}
