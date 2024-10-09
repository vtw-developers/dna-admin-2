package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.common.author.AuditQuery;
import com.vtw.dna.integration.manage.api.HttpMethod;
import com.vtw.dna.integration.manage.api.dto.RequestParameter;
import lombok.Data;

import java.util.List;

@Data
public class FlowQuery extends AuditQuery {
    private Long sid;
    private FlowType flowType;
    private String flowId;
    private String name;
    private HttpMethod httpMethod;
    private String url;
    private List<RequestParameter> requestParameters;
    private DataSchemaView responseBody;
    private Boolean templated;
    private Long templateSid;
    private String templateId;
    private String templateName;
    private List<ParameterValue> parameters;

    public FlowMeta convert() {
        FlowMeta meta = new FlowMeta();
        meta.setId(flowId);
        meta.setName(name);
        meta.setHttpMethod(httpMethod.name());
        meta.setPath(url);
        meta.setRequestParameters(requestParameters);
        meta.setResponseBody(responseBody.convert());

        Templated templated = new Templated();
        templated.setRef(templateId);
        templated.setParameters(ParameterValue.convert(parameters));
        meta.setTemplate(templated);

        return meta;
    }
}
