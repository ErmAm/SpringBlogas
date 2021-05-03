package lt.codeacademy.blogas.repository;

import lt.codeacademy.blogas.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
