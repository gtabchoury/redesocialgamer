package rsg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rsg.model.Post;
import rsg.model.User;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    public List<Post> getAllByUser(User user);
    public Post getById(Long id);
}
