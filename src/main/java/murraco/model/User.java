package murraco.model;

import lombok.Data;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="id")
	@SequenceGenerator(name="id", sequenceName="user_seq", allocationSize=1)
	private Long id;

	@Column
	private String name;

	@Column
	private String username;

	@Column
	private String email;

	@Column
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	List<Role> roles;
}
