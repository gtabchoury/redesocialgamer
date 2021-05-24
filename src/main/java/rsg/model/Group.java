package rsg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "groups")
public class Group {

	public static final String TYPE_PRIVATE = "PRIVATE";
	public static final String TYPE_PUBLIC = "PUBLIC";

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="id")
	@SequenceGenerator(name="id", sequenceName="group_seq", allocationSize=1)
	private Long id;

	@Column
	private Date creationDate;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String type;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;

	public Boolean isPublic(){
		return this.type!=null && this.type.equals(TYPE_PUBLIC);
	}

	public Boolean isPrivate(){
		return this.type!=null && this.type.equals(TYPE_PRIVATE);
	}
}
