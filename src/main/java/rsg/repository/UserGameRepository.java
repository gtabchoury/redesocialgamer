package rsg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rsg.model.Game;
import rsg.model.User;
import rsg.model.UserGame;

import java.util.List;

public interface UserGameRepository extends JpaRepository<UserGame, Integer> {
    List<UserGame> findByUserAndActiveTrue(User user);
    Page<UserGame> findByUserAndActiveTrue(User user, Pageable pageable);
    UserGame getByUserAndGame(User user, Game game);
}
