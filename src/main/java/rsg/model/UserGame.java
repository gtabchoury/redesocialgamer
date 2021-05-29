package rsg.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_games")
public class UserGame extends BaseModel{
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="user_game_seq")
	@SequenceGenerator(name="user_game_seq", sequenceName="user_game_seq", allocationSize=1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;
}
