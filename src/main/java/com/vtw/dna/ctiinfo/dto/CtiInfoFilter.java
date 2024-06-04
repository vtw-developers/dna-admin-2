package com.vtw.dna.ctiinfo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class CtiInfoFilter {
    private String name;
    private Boolean enabled;
    private Long serviceGroupId;
}
