package rsg.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import rsg.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User getById(Long id);
	boolean existsByUsername(String username);
	User findByUsername(String username);
}
