package rsg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rsg.model.Group;
import rsg.model.GroupMember;
import rsg.model.User;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {
    GroupMember getById(Long id);
    GroupMember findByUserAndAndGroup(User user, Group group);
    List<GroupMember> findByGroupAndApproved(Group group, Boolean approved);
}
