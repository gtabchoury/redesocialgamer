package rsg.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import rsg.model.File;

@Data
@AllArgsConstructor
public class FileDTO {
    private Long id;
    private String name;
    private String url;
    private String type;

    public FileDTO(File file){
        this.id=file.getId();
        this.name=file.getName();
        this.type=file.getType();
        this.url="";
    }
}