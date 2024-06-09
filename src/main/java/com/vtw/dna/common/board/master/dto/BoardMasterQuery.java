package com.vtw.dna.common.board.master.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

@Data
public class BoardMasterQuery extends AuditQuery {
    private Long id;
    private String name;
    private String instruction;
    private Boolean fileAttachYn;
    private Boolean replyYn;
    private Boolean useYn;
    private Boolean commentYn;
}
