package rsg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rsg.model.Game;
import rsg.model.GameRate;
import rsg.model.User;
import rsg.model.UserGame;

import java.util.List;

public interface GameRateRepository extends JpaRepository<GameRate, Integer> {
    GameRate findByUserAndGame(User user, Game game);
    List<GameRate> findByGame(Game game);

    @Query(value = "select avg(rate) from game_rates where game_id=?1", nativeQuery = true)
    Double getScore(Long idGame);
}
