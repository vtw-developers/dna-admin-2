package com.vtw.dna.apiinfo;

import lombok.Data;
import org.springframework.http.HttpMethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ApiInfo {
    private Long id;

    @Size(max = 100)
    @NotBlank
    private String name;

    private HttpMethod httpMethod;

    private String url;
}
