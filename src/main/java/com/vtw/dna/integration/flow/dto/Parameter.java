package com.vtw.dna.integration.flow.dto;

import lombok.Data;

@Data
public class Parameter {
    private String name;
    private String type;
    private String description;
    private String defaultValue;
}
