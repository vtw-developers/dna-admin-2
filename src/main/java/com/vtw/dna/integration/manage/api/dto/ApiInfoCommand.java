package com.vtw.dna.integration.manage.api.dto;

import com.vtw.dna.integration.manage.api.HttpMethod;
import com.vtw.dna.common.author.SignInAuthor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ApiInfoCommand implements SignInAuthor {
    private Long id;

    @Size(max = 100)
    @NotBlank
    private String name;

    private HttpMethod httpMethod;

    private String url;

    private Boolean enabled;

    private Long serviceGroupId;

    private List<RequestParameter> requestParameters;
    private List<ResponseElements> responseElements;

    private String flowId;
    private String flowMetaYaml;

}
