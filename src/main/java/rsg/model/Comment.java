package rsg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="id")
	@SequenceGenerator(name="id", sequenceName="comment_seq", allocationSize=1)
	private Long id;

	@Column
	private String text;

	@Column
	private Date date;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
}
