package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.common.author.SignInAuthor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class TemplatedFlowCommand implements SignInAuthor {
    private Long id;

    @Size(max = 100)
    @NotBlank
    private String name;
    private Long templateSid;
    private List<ParameterValue> parameters;
}
