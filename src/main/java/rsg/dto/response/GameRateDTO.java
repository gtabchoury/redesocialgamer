package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Game;
import rsg.model.GameRate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GameRateDTO {
	private Double score;
	private List<UserGameRateDTO> rates;

	public GameRateDTO(Double score, List<GameRate> gameRates){
		this.score=score;
		if (gameRates!=null){
			this.rates=gameRates.stream().map(UserGameRateDTO::new).collect(Collectors.toList());
		}else{
			this.rates=new ArrayList<>();
		}
	}
}
