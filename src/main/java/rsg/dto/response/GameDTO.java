package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Game;
import rsg.model.Group;

@Data
@NoArgsConstructor
public class GameDTO {
	private Long id;
	private String name;
	private String description;
	private Double ratingScore;

	public GameDTO(Game game){
		this.id=game.getId();
		this.name=game.getName();
		this.description=game.getDescription();
		this.ratingScore=game.getRatingScore();
	}
}
