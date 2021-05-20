package murraco.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "auth_token")
public class AuthToken {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="id")
	@SequenceGenerator(name="id", sequenceName="auth_token_seq", allocationSize=1)
	private Long id;

	@Column
	private String accessToken;

	@Column
	private String refreshToken;

	@Column
	private Date expirationDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
