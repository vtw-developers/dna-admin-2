package com.vtw.dna.common.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserQuery {
    private String id;
    private String name;
    private Long roleId;
    private String roleName;
    private Long roleLevel;
    private boolean approval;
    private LocalDateTime approvalTime;
}

