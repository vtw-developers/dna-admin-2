package com.vtw.dna.common.rest;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private int status;
    private String code;
    private String message;
    private Object details;
}
