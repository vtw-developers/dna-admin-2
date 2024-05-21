package com.vtw.dna.common.rest;

import lombok.Data;

/*
 * Rest API 오류 발생 시 에러 내용을 응답한다.
 */
@Data
public class ErrorResponse {
    /*
     * 자체 정의한 에러 코드
     */
    private String code;

    /*
     * 상세 에러 내용
     */
    private String message;
}
