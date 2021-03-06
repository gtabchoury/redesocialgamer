package rsg.dto.request;

import lombok.Data;
import rsg.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegisterDTO {
	@NotNull
	@Size(min = 5)
	private String username;

	@NotNull
	@Size(min = 5)
	private String name;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Size(min = 5)
	private String password;

	public User getModel(){
		User user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		return user;
	}
}
