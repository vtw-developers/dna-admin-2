package com.vtw.dna.integration.manage.group.dto;

import com.vtw.dna.author.SignInAuthor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ServiceGroupCommand implements SignInAuthor {
    private Long id;

    @Size(max = 100)
    @NotBlank
    private String name;

    private String description;
}
