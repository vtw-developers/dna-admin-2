package com.vtw.dna.servicegroup.dto;

import com.vtw.dna.apiinfo.HttpMethod;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ServiceGroupFilter {
    private String name;
}
