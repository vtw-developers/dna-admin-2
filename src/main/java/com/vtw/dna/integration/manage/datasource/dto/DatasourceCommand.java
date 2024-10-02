package com.vtw.dna.integration.manage.datasource.dto;

import com.vtw.dna.common.author.SignInAuthor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class DatasourceCommand implements SignInAuthor {
    private Long id;

    @Size(max = 100)
    @NotBlank
    private String name;

    private String database;
    private String url;
    private String username;
    private String password;
}
