package rsg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rsg.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment getById(Long id);
}
