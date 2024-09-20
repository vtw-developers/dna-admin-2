package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

@Data
public class FlowTemplateQuery extends AuditQuery {
    private Long id;
    private String name;
}
