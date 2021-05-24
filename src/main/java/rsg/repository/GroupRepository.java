package rsg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rsg.model.Group;
import rsg.model.User;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    public List<Group> getAllByOwner(User owner);
    public Group getById(Long id);
}
