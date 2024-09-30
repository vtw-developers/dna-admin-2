package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class FlowTemplateQuery extends AuditQuery {
    private Long sid;
    private FlowType flowType;
    private String name;
    private String templateId;
    private List<TemplateParameter> parameters = new ArrayList<>();

    public FlowTemplateMeta convert() {
        FlowTemplateMeta meta = new FlowTemplateMeta();
        meta.setName(name);
        meta.setId(templateId);
        for (TemplateParameter parameter : parameters) {
            TemplateParameterMeta parameterMeta = new TemplateParameterMeta();
            parameterMeta.setType(StringUtils.uncapitalize(parameter.getType()));
            parameterMeta.setDescription(parameter.getDescription());
            parameterMeta.setDefaultValue(parameter.getDefaultValue());
            meta.getParameters().put(parameter.getName(), parameterMeta);
        }
        return meta;
    }
}
