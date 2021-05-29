package rsg.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "game_rates")
public class GameRate extends BaseModel{
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="game_rate_seq")
	@SequenceGenerator(name="game_rate_seq", sequenceName="game_rate_seq", allocationSize=1)
	private Long id;

	@Column
	private Double rate;

	@Column
	private String text;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;
}
