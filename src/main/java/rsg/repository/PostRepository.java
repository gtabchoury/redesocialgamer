package rsg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rsg.model.Post;
import rsg.model.User;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post getById(Long id);
    List<Post> findAllByUser(User user);
    Page<Post> findAllByUser(User user, Pageable pageable);
}
