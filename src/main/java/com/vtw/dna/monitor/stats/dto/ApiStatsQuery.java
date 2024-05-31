package com.vtw.dna.monitor.stats.dto;

import com.vtw.dna.author.AuditQuery;
import com.vtw.dna.monitor.log.ServiceResult;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ApiStatsQuery extends AuditQuery {
    private String serviceGroup;
    private String api;
    private LocalDate date;
    private long count;
}
