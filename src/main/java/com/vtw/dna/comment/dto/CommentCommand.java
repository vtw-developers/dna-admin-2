package com.vtw.dna.comment.dto;

import com.vtw.dna.author.LoginUserAuthor;
import lombok.Data;

@Data
public class CommentCommand implements LoginUserAuthor {
    private Long id;
    private Long boardId;
    private Long commentNo;
    private String content;
    private Long parentId;
    private Boolean useYn;
}
