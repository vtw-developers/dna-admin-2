package com.vtw.dna.integration.monitor.log.cti.dto;

import com.vtw.dna.integration.monitor.log.ServiceResult;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class CtiLogFilter {
    private ServiceResult result;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toTime;

    private Long serviceGroupId;
    private Long ctiInfoId;
}
