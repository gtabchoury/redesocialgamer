package rsg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "games")
public class Game {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="id")
	@SequenceGenerator(name="id", sequenceName="game_seq", allocationSize=1)
	private Long id;

	@Column(columnDefinition = "boolean default true")
	private Boolean active;

	@Column
	private String name;

	@Column
	private String description;

	@Transient
	private Double ratingScore;
}
