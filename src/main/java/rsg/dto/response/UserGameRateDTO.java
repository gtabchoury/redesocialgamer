package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.GameRate;

import java.util.List;

@Data
@NoArgsConstructor
public class UserGameRateDTO {
	private Double rate;
	private String text;
	private SimpleUserDTO user;

	public UserGameRateDTO(GameRate gameRate){
		this.rate=gameRate.getRate();
		this.text=gameRate.getText();
		this.user=new SimpleUserDTO(gameRate.getUser());
	}
}
