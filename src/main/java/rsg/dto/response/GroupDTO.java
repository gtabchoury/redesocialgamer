package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Group;

import java.util.Date;

@Data
@NoArgsConstructor
public class GroupDTO {
	private Long id;
	private String name;
	private String description;
	private UserDTO owner;

	public GroupDTO(Group group){
		this.id=group.getId();
		this.name=group.getName();
		this.description=group.getDescription();
		this.owner=new UserDTO(group.getOwner());
	}
}
