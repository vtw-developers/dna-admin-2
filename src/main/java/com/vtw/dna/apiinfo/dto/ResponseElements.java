package com.vtw.dna.apiinfo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class ResponseElements {
    private String name;
    private String type;
    private String defaultValue;
}
