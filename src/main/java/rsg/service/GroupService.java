package rsg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rsg.dto.request.CreateGroupDTO;
import rsg.exception.CustomException;
import rsg.model.Group;
import rsg.model.User;
import rsg.repository.GroupRepository;
import java.util.Date;
import java.util.List;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	public Group create(CreateGroupDTO groupRequest, User user){
		Group group = groupRequest.getModel();
		group.setCreationDate(new Date());
		group.setOwner(user);
		groupRepository.save(group);
		return group;
	}

	public List<Group> getByUser(User user){
		return groupRepository.getAllByOwner(user);
	}

	public Group getById(Long id){
		return groupRepository.getById(id);
	}

	public Group join(Group group, User newUser){
		if (group.isPublic()){
			return group;
		}else{
			throw new CustomException("This is a private group.", HttpStatus.FORBIDDEN);
		}
	}
}
