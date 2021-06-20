package rsg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
public class File extends BaseModel{
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="file_seq")
	@SequenceGenerator(name="file_seq", sequenceName="file_seq", allocationSize=1)
	private Long id;

	private String name;

	private String type;

	private String extension;

	public File(String name, String type, String extension) {
		this.name = name;
		this.type = type;
		this.extension=extension;
	}
}
