package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostCommentDTO {
    private Integer count;
    private List<CommentDTO> comments;

    public PostCommentDTO(List<Comment> comments){
        if (comments==null){
            this.count=0;
            this.comments=new ArrayList<>();
        }else{
            System.out.println(">>>>>"+comments.size());
            this.count=comments.size();
            this.comments=comments.stream().map(CommentDTO::new).collect(Collectors.toList());
        }
    }
}