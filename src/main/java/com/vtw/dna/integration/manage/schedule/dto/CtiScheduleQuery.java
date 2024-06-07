package com.vtw.dna.integration.manage.schedule.dto;

import com.vtw.dna.author.AuditQuery;
import lombok.Data;

@Data
public class CtiScheduleQuery extends AuditQuery {
    private Long id;
    private String serviceGroupName;
    private Long ctiInfoId;
    private String ctiInfoName;
    private String cronExpr;
}
