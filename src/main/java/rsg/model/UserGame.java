package rsg.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_games")
public class UserGame {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="id")
	@SequenceGenerator(name="id", sequenceName="user_game_seq", allocationSize=1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;
}
