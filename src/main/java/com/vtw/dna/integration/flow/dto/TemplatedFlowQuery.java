package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

@Data
public class TemplatedFlowQuery extends AuditQuery {
    private Long id;
    private String name;
    private Long templateId;
    private String templateName;
}
