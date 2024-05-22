package com.vtw.dna.common.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<?> handleIllegalArgumentException(MissingServletRequestParameterException e) {
        log.error("에러 났어!", e);
//        final ErrorResponse errorResponse = ErrorResponse.builder()
//                .code("Item Not Found")
//                .message(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getParameterName() + "을 입력해야돼");
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleIllegalArgumentException(Exception e) {
        log.error("에러 났어!", e);
//        final ErrorResponse errorResponse = ErrorResponse.builder()
//                .code("Item Not Found")
//                .message(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<?> handleIllegalArgumentException(NoSuchElementException e) {
        log.error("NoSuchElementException occurred.", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorCode.NoSuchElement);
    }
}
