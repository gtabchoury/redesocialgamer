package rsg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rsg.model.Game;
import rsg.model.User;
import rsg.model.UserGame;

import java.util.List;

public interface UserGameRepository extends JpaRepository<UserGame, Integer> {
    List<UserGame> findByUser(User user);
    UserGame getByUserAndGame(User user, Game game);
}
