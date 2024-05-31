package com.vtw.dna.boardmaster.dto;

import com.vtw.dna.author.AuditQuery;
import lombok.Data;

@Data
public class BoardMasterQuery extends AuditQuery {
    private Long id;
    private String name;
    private String instruction;
    private Boolean fileAttachYn;
    private Boolean replyYn;
    private Boolean useYn;
}
