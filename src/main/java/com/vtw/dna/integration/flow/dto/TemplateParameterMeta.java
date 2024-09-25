package com.vtw.dna.integration.flow.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({"type", "description", "defaultValue"})
@Data
public class TemplateParameterMeta {
    private String type;
    private String description;
    private String defaultValue;
}
