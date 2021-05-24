package rsg.dto.request;

import lombok.Data;

@Data
public class UserRegisterDTO {
	private String username;
	private String name;
	private String email;
	private String password;
}
