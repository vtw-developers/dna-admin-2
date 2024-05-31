package com.vtw.dna.monitor.log.dto;

import com.vtw.dna.apiinfo.HttpMethod;
import com.vtw.dna.author.AuditQuery;
import com.vtw.dna.monitor.log.ServiceResult;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiLogQuery extends AuditQuery {
    private String messageId;
    private Long apiInfoId;
    private LocalDateTime timestamp;
    private ServiceResult result;
    private String errorCode;
    private String errorMessage;
    private String flowName;
    private String details;
    private long elapsedTime;

    private String apiInfoName;
    private String serviceGroupName;
}
