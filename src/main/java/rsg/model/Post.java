package rsg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "posts")
public class Post extends BaseModel{
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="post_seq")
	@SequenceGenerator(name="post_seq", sequenceName="post_seq", allocationSize=1)
	private Long id;

	@Column
	private String title;

	@Column
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<Comment> comments;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<Like> likes;
}
