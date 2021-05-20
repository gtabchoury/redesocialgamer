package murraco.dto.response;

import lombok.Data;
import murraco.model.AuthToken;

import java.util.Date;

@Data
public class TokenDTO {
	private String access_token;
	private String refresh_token;
	private Long expires_in;
	private Date expirationDate;

	public TokenDTO(){

	}

	public TokenDTO(AuthToken authToken){
		this.access_token=authToken.getAccessToken();
		this.refresh_token=authToken.getRefreshToken();
		this.expires_in=authToken.getExpirationDate().getTime() -(new Date()).getTime();
		this.expirationDate=authToken.getExpirationDate();
	}
}
