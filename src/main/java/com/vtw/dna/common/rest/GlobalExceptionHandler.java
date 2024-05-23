package com.vtw.dna.common.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<?> handle(Exception e) {
        log.error("UnregisteredError", e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponse response = ErrorResponse.builder()
                .status(status.value())
                .code("UnregisteredError")
                .message("등록되지 않은 오류가 발생하였습니다.")
                .details(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler
    protected ResponseEntity<?> handle(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException occurred.", e);

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse response = ErrorResponse.builder()
                .status(status.value())
                .code("MissingRequestParameter")
                .message("요청 파라미터가 누락되었습니다.")
                .details(Map.of("missingParameter", e.getParameterName())).build();
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler
    protected ResponseEntity<?> handle(NoSuchEntityException e) {
        log.error("NoSuchEntityException occurred.", e);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> details = new LinkedHashMap<>();
        details.put("entityName", e.getEntityName());
        details.put("entityId", e.getEntityId());

        ErrorResponse response = ErrorResponse.builder()
                .status(status.value())
                .code("NoSuchEntity")
                .message("해당 정보를 찾을 수 없습니다.")
                .details(details).build();
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler
    protected ResponseEntity<?> handle(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException occurred.", e);

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, Object> details = new LinkedHashMap<>();
        details.put("parameterName", e.getParameter().getParameterName());
        details.put("parameterType", e.getParameter().getParameterType().getName());
        details.put("requestValue", e.getValue().toString());

        ErrorResponse response = ErrorResponse.builder()
                .status(status.value())
                .code("InvalidRequestParameterType")
                .message("요청 파라미터의 타입이 올바르지 않습니다.")
                .details(details).build();
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler
    protected ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException occurred.", e);

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, Object> details = new LinkedHashMap<>();
        details.put("field", e.getFieldError().getField());
        details.put("value", e.getFieldError().getRejectedValue());
        details.put("rule", e.getFieldError().getCode());

        ErrorResponse response = ErrorResponse.builder()
                .status(status.value())
                .code("InvalidValidation")
                .message("엔티티의 유효성 검사가 올바르지 않습니다.")
                .details(details).build();
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler
    protected ResponseEntity<?> handle(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException occurred.", e);

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse response = ErrorResponse.builder()
                .status(status.value())
                .code("HttpRequestMethodNotSupported")
                .message("올바르지 않은 HTTP Method 입니다.")
                .details(e.getMessage()).build();
        return ResponseEntity.status(status).body(response);
    }
}
