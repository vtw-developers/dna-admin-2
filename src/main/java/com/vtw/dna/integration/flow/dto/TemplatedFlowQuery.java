package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

import java.util.List;

@Data
public class TemplatedFlowQuery extends AuditQuery {
    private Long id;
    private String name;
    private List<ParameterValue> parameters;
    private Long templateSid;
    private String templateName;
}
