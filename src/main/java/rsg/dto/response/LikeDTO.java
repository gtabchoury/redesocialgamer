package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Comment;
import rsg.model.Like;

import java.util.Date;

@Data
@NoArgsConstructor
public class LikeDTO {
	private Long id;
	private Date date;
	private SimpleUserDTO user;

	public LikeDTO(Like like){
		this.id=like.getId();
		this.date=like.getDate();
		this.user=new SimpleUserDTO(like.getUser());
	}
}
