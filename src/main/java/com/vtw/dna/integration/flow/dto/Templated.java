package com.vtw.dna.integration.flow.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Templated {
    private String ref;
    private Map<String, Object> parameters;
}
