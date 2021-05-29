package rsg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import rsg.model.Game;
import java.util.List;

public interface GameRepository extends PagingAndSortingRepository<Game, Integer> {
    Game getById(Long id);
    Page<Game> findAllByActiveTrue(Pageable pageable);
    List<Game> findAllByActiveTrue();
}
