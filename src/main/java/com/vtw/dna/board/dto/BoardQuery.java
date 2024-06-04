package com.vtw.dna.board.dto;

import com.vtw.dna.author.AuditQuery;
import lombok.Data;

@Data
public class BoardQuery extends AuditQuery {
    private Long id;
    private Long boardNo;
    private String title;
    private String content;
    private boolean useYn;
}
