package rsg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rsg.model.Group;
import rsg.model.User;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group getById(Long id);
    List<Group> findAllByOwner(User owner);
    Page<Group> findAllByOwner(User owner, Pageable pageable);
}
