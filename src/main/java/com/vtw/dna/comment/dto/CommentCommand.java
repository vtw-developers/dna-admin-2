package com.vtw.dna.comment.dto;

import com.vtw.dna.author.SignInAuthor;
import lombok.Data;

@Data
public class CommentCommand implements SignInAuthor {
    private Long id;
    private Long boardId;
    private Long commentNo;
    private String content;
    private Long parentId;
    private Boolean useYn;
}
