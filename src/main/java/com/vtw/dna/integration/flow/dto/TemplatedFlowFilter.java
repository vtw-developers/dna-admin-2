package com.vtw.dna.integration.flow.dto;

import com.vtw.dna.integration.manage.api.HttpMethod;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TemplatedFlowFilter {
    private String name;
}
