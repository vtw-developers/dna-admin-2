package com.vtw.dna.integration.manage.group.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

@Data
public class ServiceGroupQuery extends AuditQuery {
    private Long id;
    private String name;
    private String description;
}
