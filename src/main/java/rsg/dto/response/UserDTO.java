package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.User;
import rsg.utils.FilesUtils;

@Data
@NoArgsConstructor
public class UserDTO {
	private Long id;
	private String name;
	private String username;
	private String email;
	private String profilePhoto;

	public UserDTO(User user){
		this.id=user.getId();
		this.name=user.getName();
		this.username=user.getUsername();
		this.email=user.getEmail();
		this.profilePhoto= FilesUtils.getUserProfilePhotoUrl(user.getProfilePhoto());
	}
}
