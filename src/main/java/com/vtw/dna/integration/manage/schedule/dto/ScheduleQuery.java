package com.vtw.dna.integration.manage.schedule.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

@Data
public class ScheduleQuery extends AuditQuery {
    private Long id;
    private Long flowSid;
    private String flowId;
    private String flowName;
    private String cronExpr;
    private String status;
}
