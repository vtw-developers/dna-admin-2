package com.vtw.dna.integration.manage.cti.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

@Data
public class CtiInfoQuery extends AuditQuery {
    private Long id;
    private String name;
    private Boolean enabled;
    private String serviceGroupId;
    private String serviceGroupName;
    private String flowId;
    private String flowMetaYaml = "";
}
