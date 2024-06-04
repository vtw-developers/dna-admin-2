package com.vtw.dna.monitor.log.dto;

import com.vtw.dna.apiinfo.HttpMethod;
import com.vtw.dna.author.AuditQuery;
import com.vtw.dna.monitor.log.ServiceResult;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiLogQuery {
    private String messageId;
    private String flowId;
    private LocalDateTime timestamp;
    private ServiceResult result;
    private String errorCode;
    private String errorMessage;
    private String flowName;
    private String details;
    private long elapsedTime;

    private Long apiInfoId;
    private String apiInfoName;
    private Long serviceGroupId;
    private String serviceGroupName;
}
