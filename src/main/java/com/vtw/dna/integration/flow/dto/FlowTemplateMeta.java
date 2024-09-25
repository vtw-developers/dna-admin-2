package com.vtw.dna.integration.flow.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({"name", "templateId", "parameters"})
@Data
public class FlowTemplateMeta {
    private String name;
    private String templateId;
    private Map<String, TemplateParameterMeta> parameters = new LinkedHashMap<>();

    public FlowTemplateQuery convert() {
        FlowTemplateQuery query = new FlowTemplateQuery();
        query.setName(name);
        query.setTemplateId(templateId);

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
