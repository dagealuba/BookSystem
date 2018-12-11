package serviceImpl;

import entity.Comment;
import factory.DaoFactory;
import service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> getCommentsByBookId(String bookId) {
        return DaoFactory.getCommentDaoImpl().getComment(bookId);
    }

    @Override
    public boolean newComment(Comment comment) {
        return DaoFactory.getCommentDaoImpl().insertComment(comment);
    }

    @Override
    public boolean updateComment(Comment comment) {
        return DaoFactory.getCommentDaoImpl().updateComment(comment);
    }

    @Override
    public boolean deleteComment(Comment comment) {
        return DaoFactory.getCommentDaoImpl().deleteComment(comment.getCommentId());
    }
}
