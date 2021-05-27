package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Comment;
import rsg.model.Post;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentDTO {
	private Long id;
	private String text;
	private Date date;
	private SimpleUserDTO user;

	public CommentDTO(Comment comment){
		this.id=comment.getId();
		this.text=comment.getText();
		this.date=comment.getDate();
		this.user=new SimpleUserDTO(comment.getUser());
	}
}
