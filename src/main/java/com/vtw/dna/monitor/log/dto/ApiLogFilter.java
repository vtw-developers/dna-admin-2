package com.vtw.dna.monitor.log.dto;

import com.vtw.dna.apiinfo.HttpMethod;
import com.vtw.dna.monitor.log.ServiceResult;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ApiLogFilter {
    private ServiceResult result;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toTime;

    private Long serviceGroupId;
    private Long apiInfoId;
}
