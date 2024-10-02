package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.common.author.SignInAuthor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class FlowTemplateCommand implements SignInAuthor {
    @Id
    private Long sid;

    private FlowType flowType;

    @Size(max = 100)
    @NotBlank
    private String name;
    private String templateId;
    private List<TemplateParameter> parameters;
}
