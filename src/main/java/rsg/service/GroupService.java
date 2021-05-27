package rsg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rsg.dto.request.CreateGroupDTO;
import rsg.exception.CustomException;
import rsg.model.Group;
import rsg.model.GroupMember;
import rsg.model.User;
import rsg.repository.GroupMemberRepository;
import rsg.repository.GroupRepository;
import java.util.Date;
import java.util.List;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private GroupMemberRepository groupMemberRepository;

	public Group create(CreateGroupDTO groupRequest, User user){
		Group group = groupRequest.getModel();
		group.setCreationDate(new Date());
		group.setOwner(user);
		groupRepository.save(group);
		GroupMember groupMember = new GroupMember();
		groupMember.setUser(user);
		groupMember.setGroup(group);
		groupMember.setJoinRequestDate(new Date());
		groupMember.setJoinDate(new Date());
		groupMember.setApproved(true);
		groupMemberRepository.save(groupMember);
		return group;
	}

	public List<Group> getByUser(User user){
		return groupRepository.findAllByOwner(user);
	}

	public Group getById(Long id){
		return groupRepository.getById(id);
	}

	public GroupMember join(Group group, User newUser){
		GroupMember groupMember = groupMemberRepository.findByUserAndAndGroup(newUser, group);
		if (groupMember!=null){
			if (groupMember.getApproved()) {
				throw new CustomException("User is already a member of the group.", HttpStatus.BAD_REQUEST);
			}else if (!group.isPublic()){
				throw new CustomException("Request to join already sent.", HttpStatus.BAD_REQUEST);
			}else{
				groupMember.setApproved(true);
				groupMember.setJoinDate(new Date());
			}
		}else{
			groupMember = new GroupMember();
			groupMember.setGroup(group);
			groupMember.setUser(newUser);
			groupMember.setJoinRequestDate(new Date());

			if (group.getOwner().equals(newUser) || group.isPublic()){
				groupMember.setApproved(true);
				groupMember.setJoinDate(new Date());
			}else{
				groupMember.setApproved(false);
			}
		}
		groupMemberRepository.save(groupMember);
		return groupMember;
	}

	public GroupMember approve(User user, Group group, GroupMember groupMember){
		if (!groupMember.getGroup().equals(group))
			throw new CustomException("Invalid request", HttpStatus.BAD_REQUEST);

		if (!group.getOwner().equals(user))
			throw new CustomException("User not allowed to approve this request", HttpStatus.FORBIDDEN);

		if (groupMember.getApproved())
			throw new CustomException("User is already a member of the gorup", HttpStatus.BAD_REQUEST);

		groupMember.setApproved(true);
		groupMember.setJoinDate(new Date());
		groupMemberRepository.save(groupMember);
		return groupMember;
	}

	public void decline(User user, Group group, GroupMember groupMember){
		if (!groupMember.getGroup().equals(group))
			throw new CustomException("Invalid request", HttpStatus.BAD_REQUEST);

		if (!group.getOwner().equals(user))
			throw new CustomException("User not allowed to decline this request", HttpStatus.FORBIDDEN);

		if (groupMember.getApproved())
			throw new CustomException("User is already a member of the gorup", HttpStatus.BAD_REQUEST);

		groupMemberRepository.delete(groupMember);
	}

	public void removeMember(User loggedUser, Group group, User member){
		GroupMember groupMember = groupMemberRepository.findByUserAndAndGroup(member, group);

		if (groupMember==null)
			throw new CustomException("User is not a member of the group", HttpStatus.FORBIDDEN);

		if (!group.getOwner().equals(loggedUser) && !loggedUser.equals(member))
			throw new CustomException("User not allowed to remove member", HttpStatus.FORBIDDEN);

		groupMemberRepository.delete(groupMember);
	}
}
