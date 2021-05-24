package rsg.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Group;
import rsg.model.Post;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateGroupDTO {
	@NotNull
	@Size(min=5)
	private String name;

	@NotNull
	@Size(min=5)
	private String description;

	@NotNull
	private Boolean isPublic;

	public Group getModel(){
		Group group = new Group();
		group.setName(this.name);
		group.setDescription(this.description);
		group.setType(isPublic ? Group.TYPE_PUBLIC : Group.TYPE_PRIVATE);
		return group;
	}
}
