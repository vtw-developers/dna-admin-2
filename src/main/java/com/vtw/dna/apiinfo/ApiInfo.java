package com.vtw.dna.apiinfo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ApiInfo {
    private Long id;

    @Size(max = 1)
    @NotBlank
    private String name;
}
