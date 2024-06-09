package com.vtw.dna.integration.monitor.stats.api.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ApiStatsQuery extends AuditQuery {
    private String serviceGroup;
    private String api;
    private LocalDate date;
    private long count;
}
