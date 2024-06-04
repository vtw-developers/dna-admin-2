package com.vtw.dna.ctiinfo.dto;

import com.vtw.dna.author.AuditQuery;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
