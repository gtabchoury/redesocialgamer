package rsg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rsg.model.Comment;
import rsg.model.Like;
import rsg.model.Post;
import rsg.model.User;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    Like getById(Long id);
    Like findByUserAndPost(User user, Post post);
}
