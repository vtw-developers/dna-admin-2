package com.vtw.dna.common.board.comment.dto;

import com.vtw.dna.common.author.SignInAuthor;
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
