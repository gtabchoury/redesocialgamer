package rsg.dto.request;

import lombok.Data;
import rsg.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserEditPasswordDTO {
	@NotNull
	private String currentPassword;

	@NotNull
	@Size(min = 5)
	private String newPassword;
}
