package rsg.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseModel {
	@Column
	protected Date creationDate;

	@Column
	protected Date changeDate;

	@Column
	protected Boolean removed=false;

	@Column
	protected Boolean active=true;
}
