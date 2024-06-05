package com.vtw.dna.board.dto;

import com.vtw.dna.author.AuditQuery;
import lombok.Data;

import java.util.List;

@Data
public class BoardQuery extends AuditQuery {
    private Long id;
    private Long boardNo;
    private Long parentId;
    private Long viewCount;
    private String title;
    private String content;
    private List<BoardFile> files;
    private boolean useYn;
}
