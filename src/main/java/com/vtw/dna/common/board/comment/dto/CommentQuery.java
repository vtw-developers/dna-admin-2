package com.vtw.dna.common.board.comment.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

@Data
public class CommentQuery extends AuditQuery {
    private Long id;
    private Long boardId;
    private Long commentNo;
    private String content;
    private boolean useYn;
}
