package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.common.author.AuditQuery;
import com.vtw.dna.integration.flow.repository.FlowTemplateRepository;
import com.vtw.dna.integration.flow.repository.TemplatedFlowRepository;
import com.vtw.dna.integration.manage.api.HttpMethod;
import com.vtw.dna.integration.manage.api.dto.RequestParameter;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class TemplatedFlowMeta extends AuditQuery {
    private String id;
    private String name;
    private String httpMethod;
    private String path;
    private String tag;
    private List<RequestParameter> requestParameters;
    private DataSchema responseBody;
    private String outType;
    private Templated template;

    public TemplatedFlowQuery convert(FlowTemplateRepository repository) {
        TemplatedFlowQuery query = new TemplatedFlowQuery();
        query.setFlowId(id);
        query.setName(name);
        query.setHttpMethod(HttpMethod.valueOf(httpMethod.toUpperCase()));
        query.setUrl(path);
        if (requestParameters != null) {
            query.setRequestParameters(requestParameters);
        }

        if (responseBody != null) {
            query.setResponseBody(responseBody.convert());
        }

        FlowTemplateQuery flowTemplate = repository.findByTemplateId(template.getRef()).orElseThrow();
        query.setTemplateSid(flowTemplate.getSid());
        query.setTemplateId(flowTemplate.getTemplateId());

        Map<String, Object> parameters = template.getParameters();
        List<ParameterValue> parameterValues = new ArrayList<>();
        parameters.forEach((k, v) -> {
            ParameterValue parameterValue = new ParameterValue();
            parameterValue.setName(k);
            parameterValue.setValue(v.toString());
            parameterValues.add(parameterValue);
        });
        query.setParameters(parameterValues);
        return query;
    }
}
