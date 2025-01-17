package com.vtw.dna.integration.manage.api.dto;

import lombok.Data;

@Data
public class RequestParameter {
    private String name;
    private String type;
    private int length;
    private boolean required;
    private String description;
    private String defaultValue;
}
