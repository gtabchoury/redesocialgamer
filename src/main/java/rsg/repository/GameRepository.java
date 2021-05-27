package rsg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rsg.model.Game;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    Game getById(Long id);
    List<Game> findByActiveTrue();
}
