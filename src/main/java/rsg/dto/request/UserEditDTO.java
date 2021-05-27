package rsg.dto.request;

import lombok.Data;
import rsg.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserEditDTO {
	@NotNull
	@Size(min = 5)
	private String name;

	@NotNull
	@Email
	private String email;

	public User getModel(){
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		return user;
	}
}
