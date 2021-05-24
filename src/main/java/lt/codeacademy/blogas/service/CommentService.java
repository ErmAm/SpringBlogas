package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    void addComment(Comment comment);

    Comment getComment(UUID id);

    List<Comment> getAllComments();

    void update(Comment comment);

    void delete(UUID id);
}
