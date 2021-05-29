package rsg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "games")
public class Game extends BaseModel{
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="game_seq")
	@SequenceGenerator(name="game_seq", sequenceName="game_seq", allocationSize=1)
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@Transient
	private Double ratingScore;
}
