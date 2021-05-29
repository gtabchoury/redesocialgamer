package rsg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "groups")
public class Group extends BaseModel{

	public static final String TYPE_PRIVATE = "PRIVATE";
	public static final String TYPE_PUBLIC = "PUBLIC";

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="group_seq")
	@SequenceGenerator(name="group_seq", sequenceName="group_seq", allocationSize=1)
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String type;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;

	@OneToMany(mappedBy = "group")
	private List<GroupMember> groupMembers;

	public Boolean isPublic(){
		return getType()!=null && getType().equals(TYPE_PUBLIC);
	}

	public Boolean isPrivate(){
		return getType()!=null && getType().equals(TYPE_PRIVATE);
	}
}
