package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.common.author.AuditQuery;
import com.vtw.dna.integration.flow.repository.FlowTemplateRepository;
import com.vtw.dna.integration.manage.api.HttpMethod;
import com.vtw.dna.integration.manage.api.dto.RequestParameter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class FlowMeta extends AuditQuery {
    private FlowType type;
    private String id;
    private String name;
    private String httpMethod;
    private String path;
    private String tag;
    private List<RequestParameter> requestParameters;
    private DataSchema responseBody;
    private String outType;
    private Templated template;

    public FlowQuery convert(FlowTemplateRepository repository) {
        FlowQuery query = new FlowQuery();
        query.setFlowType(type);
        if (type == null && template != null) {
            FlowTemplateQuery flowTemplate = repository.findByTemplateId(template.getRef()).orElseThrow();
            FlowType flowType = flowTemplate.getFlowType();
            query.setFlowType(flowType);
        }
        query.setFlowId(id);
        query.setName(name);

        if (query.getFlowType() == FlowType.REST) {
            query.setHttpMethod(HttpMethod.valueOf(httpMethod.toUpperCase()));
            query.setUrl(path);
            if (requestParameters != null) {
                query.setRequestParameters(requestParameters);
            }

            if (responseBody != null) {
                query.setResponseBody(responseBody.convert());
            }
        }

        if (template != null) {
            query.setTemplated(true);

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
        } else {
            query.setTemplated(false);
        }

        return query;
    }
}
