package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.common.author.SignInAuthor;
import com.vtw.dna.integration.manage.api.HttpMethod;
import com.vtw.dna.integration.manage.api.dto.RequestParameter;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class FlowCommand implements SignInAuthor {
    private Long sid;

    private FlowType flowType;

    @Size(max = 100)
    @NotBlank
    private String flowId;

    @Size(max = 100)
    @NotBlank
    private String name;

    private HttpMethod httpMethod;
    private String url;
    private List<RequestParameter> requestParameters;
    private DataSchemaView responseBody;

    private Boolean templated;
    private Long templateSid;
    private List<ParameterValue> parameters;
}
