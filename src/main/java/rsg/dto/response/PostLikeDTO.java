package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Comment;
import rsg.model.Like;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostLikeDTO {
    private Integer count;
    private List<LikeDTO> likes;

    public PostLikeDTO(List<Like> likes){
        if (likes==null){
            this.count=0;
            this.likes=new ArrayList<>();
        }else{
            this.count=likes.size();
            this.likes=likes.stream().map(LikeDTO::new).collect(Collectors.toList());
        }
    }
}