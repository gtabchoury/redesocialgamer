package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.User;

@Data
@NoArgsConstructor
public class UserDTO {
	private Long id;
	private String name;
	private String username;
	private String email;

	public UserDTO(User user){
		this.id=user.getId();
		this.name=user.getName();
		this.username=user.getUsername();
		this.email=user.getEmail();
	}
}
