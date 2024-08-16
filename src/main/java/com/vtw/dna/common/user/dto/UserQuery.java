package com.vtw.dna.common.user.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserQuery {
    private String id;
    private String name;
    private Long roleId;
    private String roleName;
}

