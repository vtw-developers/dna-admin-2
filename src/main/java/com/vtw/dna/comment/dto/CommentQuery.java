package com.vtw.dna.comment.dto;

import com.vtw.dna.author.AuditQuery;
import lombok.Data;

@Data
public class CommentQuery extends AuditQuery {
    private Long id;
    private Long boardId;
    private Long commentNo;
    private String content;
    private boolean useYn;
}
