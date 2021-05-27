package rsg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsg.model.Group;
import rsg.model.GroupMember;
import rsg.model.User;
import rsg.repository.GroupMemberRepository;
import rsg.repository.GroupRepository;

import java.util.List;

@Service
public class GroupMemberService {

	@Autowired
	private GroupMemberRepository groupMemberRepository;

	public GroupMember getById(Long id){
		return groupMemberRepository.getById(id);
	}

	public GroupMember getGroupMember(User user, Group group){
		return groupMemberRepository.findByUserAndAndGroup(user, group);
	}

	public List<GroupMember> getJoinRequests(Group group){
		return groupMemberRepository.findByGroupAndApproved(group, false);
	}

	public List<GroupMember> getMembers(Group group){
		return groupMemberRepository.findByGroupAndApproved(group, true);
	}
}
