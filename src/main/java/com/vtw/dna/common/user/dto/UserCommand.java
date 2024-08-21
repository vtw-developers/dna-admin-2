package com.vtw.dna.common.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCommand {
    private String id;
    private String name;
    private Long readRoleId;
    private boolean approval;
    private LocalDateTime approvalTime;
}
