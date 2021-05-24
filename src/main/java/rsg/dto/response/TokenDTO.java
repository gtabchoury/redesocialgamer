package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.AuthToken;

import java.util.Date;

@Data
@NoArgsConstructor
public class TokenDTO {
	private String access_token;
	private String refresh_token;
	private Long expires_in;
	private Date expirationDate;

	public TokenDTO(AuthToken authToken){
		this.access_token=authToken.getAccessToken();
		this.refresh_token=authToken.getRefreshToken();
		this.expires_in=authToken.getExpirationDate().getTime() -(new Date()).getTime();
		this.expirationDate=authToken.getExpirationDate();
	}
}
