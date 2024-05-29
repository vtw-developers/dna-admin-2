package com.vtw.dna.apiinfo.dto;

import com.vtw.dna.apiinfo.HttpMethod;
import com.vtw.dna.author.AuditQuery;
import com.vtw.dna.author.Author;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class ApiInfoQuery extends AuditQuery {
    private Long id;
    private String name;
    private HttpMethod httpMethod;
    private String url;
    private Boolean enabled;
}
