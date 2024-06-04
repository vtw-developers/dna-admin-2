package com.vtw.dna.integration.manage.cti.dto;

import com.vtw.dna.author.LoginUserAuthor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CtiInfoCommand implements LoginUserAuthor {
    private Long id;

    @Size(max = 100)
    @NotBlank
    private String name;

    private Long serviceGroupId;

    private Boolean enabled;

    private String flowId;
    private String flowMetaYaml;

}