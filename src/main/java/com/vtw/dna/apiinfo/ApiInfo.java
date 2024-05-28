package com.vtw.dna.apiinfo;

import com.vtw.dna.author.AuthorEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ApiInfo extends AuthorEntity {
    private Long id;

    @Size(max = 100)
    @NotBlank
    private String name;

    private HttpMethod httpMethod;

    private String url;

    private Boolean enabled;
}
