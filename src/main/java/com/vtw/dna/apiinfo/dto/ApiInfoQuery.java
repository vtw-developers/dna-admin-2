package com.vtw.dna.apiinfo.dto;

import com.vtw.dna.apiinfo.HttpMethod;
import com.vtw.dna.author.AuditQuery;
import com.vtw.dna.author.Author;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
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
    private String flowMetaYaml;
}
