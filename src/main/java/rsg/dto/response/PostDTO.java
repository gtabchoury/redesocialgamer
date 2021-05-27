package rsg.dto.response;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Comment;
import rsg.model.Like;
import rsg.model.Post;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostDTO {
	private Long id;
	private String title;
	private Date creationDate;
	private String description;
	private SimpleUserDTO user;
	private GroupDTO group;
	private PostCommentDTO postComments;
	private PostLikeDTO postLikes;

	public PostDTO(Post post){
		this.id=post.getId();
		this.title=post.getTitle();
		this.creationDate=post.getCreationDate();
		this.description=post.getDescription();
		this.user=new SimpleUserDTO(post.getUser());
		if (post.getGroup()!=null)
			this.group=new GroupDTO(post.getGroup());
		this.postComments=new PostCommentDTO(post.getComments());
		this.postLikes=new PostLikeDTO(post.getLikes());
	}
}
