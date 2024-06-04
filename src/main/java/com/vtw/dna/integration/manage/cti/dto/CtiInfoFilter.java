package com.vtw.dna.integration.manage.cti.dto;

import lombok.Data;

@Data
public class CtiInfoFilter {
    private String name;
    private Boolean enabled;
    private Long serviceGroupId;
}
