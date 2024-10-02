package com.vtw.dna.integration.flow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonPropertyOrder({"id", "name", "parameters"})
@Data
public class FlowTemplateMeta {
    @JsonProperty("type")
    private FlowType flowType;
    private String id;
    private String name;
    private Map<String, TemplateParameterMeta> parameters = new LinkedHashMap<>();

    public FlowTemplateQuery convert() {
        FlowTemplateQuery query = new FlowTemplateQuery();
        query.setFlowType(flowType);
        query.setName(name);
        query.setTemplateId(id);

        for (String key : parameters.keySet()) {
            TemplateParameterMeta parameterMeta = parameters.get(key);
            TemplateParameter parameter = new TemplateParameter();
            parameter.setName(key);
            parameter.setType(StringUtils.capitalize(parameterMeta.getType()));
            parameter.setDescription(parameterMeta.getDescription());
            parameter.setDefaultValue(parameterMeta.getDefaultValue());
            query.getParameters().add(parameter);
        }

        return query;
    }
}
