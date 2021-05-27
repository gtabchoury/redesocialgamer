package rsg.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Post;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateCommentDTO {
	@NotNull
	@Size(min=1)
	private String text;
}
