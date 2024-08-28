package com.vtw.dna.integration.monitor.stats.api.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ApiStatsQuery {
    private String serviceGroup;
    private String api;
    //private LocalDate date;
    int year;
    int month;

    private long count;
}
