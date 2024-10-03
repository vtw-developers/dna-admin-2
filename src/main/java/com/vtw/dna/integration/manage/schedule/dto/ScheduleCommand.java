package com.vtw.dna.integration.manage.schedule.dto;

import com.vtw.dna.common.author.SignInAuthor;
import lombok.Data;

@Data
public class ScheduleCommand implements SignInAuthor {
    private Long id;
    private Long flowSid;
    private String cronExpr;
}
