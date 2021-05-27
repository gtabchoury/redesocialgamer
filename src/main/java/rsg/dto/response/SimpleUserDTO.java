package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.User;

@Data
@NoArgsConstructor
public class SimpleUserDTO {
	private Long id;
	private String name;
	private String username;

	public SimpleUserDTO(User user){
		this.id=user.getId();
		this.name=user.getName();
		this.username=user.getUsername();
	}
}
