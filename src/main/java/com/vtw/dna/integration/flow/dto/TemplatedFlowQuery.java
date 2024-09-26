package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.common.author.AuditQuery;
import com.vtw.dna.integration.manage.api.HttpMethod;
import com.vtw.dna.integration.manage.api.dto.RequestParameter;
import lombok.Data;

import java.util.List;

@Data
public class TemplatedFlowQuery extends AuditQuery {
    private Long sid;
    private String flowId;
    private String name;
    private HttpMethod httpMethod;
    private String url;
    private List<RequestParameter> requestParameters;
    private DataSchemaView responseBody;
    private Long templateSid;
    private String templateName;
    private List<ParameterValue> parameters;
}
