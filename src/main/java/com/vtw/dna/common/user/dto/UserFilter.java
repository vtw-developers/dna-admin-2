package com.vtw.dna.common.user.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class UserFilter {
    private String id;
    private String name;
    private Long roleId;
    private Boolean approval;
}
