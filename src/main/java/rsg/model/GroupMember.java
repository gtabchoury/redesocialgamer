package rsg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "group_members")
public class GroupMember extends BaseModel{
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="group_member_seq")
	@SequenceGenerator(name="group_member_seq", sequenceName="group_member_seq", allocationSize=1)
	private Long id;

	@Column
	private Date joinDate;

	@Column
	private Date joinRequestDate;

	@Column(columnDefinition = "boolean default false")
	private Boolean approved;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
}
