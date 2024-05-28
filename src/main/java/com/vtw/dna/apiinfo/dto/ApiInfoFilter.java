package com.vtw.dna.apiinfo.dto;

import com.vtw.dna.apiinfo.HttpMethod;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ApiInfoFilter {
    private String name;
    private HttpMethod httpMethod;
    private String author;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startModifiedTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endModifiedTime;

    private Boolean enabled;
}
