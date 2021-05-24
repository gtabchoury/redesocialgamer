package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Post;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class PostDTO {
	private Long id;
	private String title;
	private Date creationDate;
	private String description;
	private UserDTO user;
	private GroupDTO group;

	public PostDTO(Post post){
		this.id=post.getId();
		this.title=post.getTitle();
		this.creationDate=post.getCreationDate();
		this.description=post.getDescription();
		this.user=new UserDTO(post.getUser());
		if (post.getGroup()!=null)
			this.group=new GroupDTO(post.getGroup());
	}
}
