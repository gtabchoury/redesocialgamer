package rsg.model;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User extends BaseModel{
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="user_seq")
	@SequenceGenerator(name="user_seq", sequenceName="user_seq", allocationSize=1)
	private Long id;

	@Column
	private String name;

	@Column
	private String username;

	@Column
	private String email;

	@Column
	private String password;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_photo_id")
	private File profilePhoto;

	@OneToMany(mappedBy = "user")
	private List<GroupMember> groupMembers;

	@ElementCollection(fetch = FetchType.EAGER)
	List<Role> roles;
}
