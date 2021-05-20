package murraco.dto.request;

import lombok.Data;

@Data
public class UserLoginDTO {
	private String username;
	private String password;
}