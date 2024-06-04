package com.vtw.dna.integration.monitor.log.cti.dto;

import com.vtw.dna.integration.monitor.log.ServiceResult;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CtiLogQuery {
    private String messageId;
    private String flowId;
    private LocalDateTime timestamp;
    private ServiceResult result;
    private String errorCode;
    private String errorMessage;
    private String flowName;
    private String details;
    private long elapsedTime;

    private Long ctiInfoId;
    private String ctiInfoName;
    private Long serviceGroupId;
    private String serviceGroupName;
}
