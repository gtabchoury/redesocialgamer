package rsg.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Post;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreatePostDTO {
	@NotNull
	@Size(min=5)
	private String title;

	@NotNull
	@Size(min=5)
	private String description;

	public Post getModel(){
		Post post = new Post();
		post.setTitle(this.title);
		post.setDescription(this.description);
		return post;
	}
}
