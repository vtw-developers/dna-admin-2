package com.vtw.dna.common.rest;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class CustomErrorResponse implements ErrorRestResponse {
    private HttpStatus status;
    private String code;
    private String message;
}
