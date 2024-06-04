package com.vtw.dna.integration.manage.api.dto;

import com.vtw.dna.integration.manage.api.HttpMethod;
import com.vtw.dna.author.AuditQuery;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiInfoQuery extends AuditQuery {
    private Long id;
    private String name;
    private HttpMethod httpMethod;
    private String url;
    private Boolean enabled;
    private String serviceGroupId;
    private String serviceGroupName;

    private List<RequestParameter> requestParameters = new ArrayList<>();
    private List<ResponseElements> responseElements = new ArrayList<>();

    private String flowId;
    private String flowMetaYaml = "";
}
