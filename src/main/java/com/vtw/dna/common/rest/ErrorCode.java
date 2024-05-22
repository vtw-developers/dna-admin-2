package com.vtw.dna.common.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonPropertyOrder({"code", "message"})
public enum ErrorCode implements ErrorRestResponse {

    Unknown(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생하였습니다."),
    NoSuchElement(HttpStatus.INTERNAL_SERVER_ERROR, "해당 정보가 존재하지 않습니다.");

    private HttpStatus httpStatus;
    private String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
