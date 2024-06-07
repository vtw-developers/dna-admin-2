package com.vtw.dna.integration.manage.schedule.dto;

import com.vtw.dna.author.SignInAuthor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CtiScheduleCommand implements SignInAuthor {
    private Long ctiInfoId;
    private String cronExpr;
}
