package com.vtw.dna.integration.monitor.stats.cti.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CtiStatsQuery extends AuditQuery {
    private String serviceGroup;
    private String cti;
    private LocalDate date;
    private long count;
}
