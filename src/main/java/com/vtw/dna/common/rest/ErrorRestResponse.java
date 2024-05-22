package com.vtw.dna.common.rest;

import org.springframework.http.HttpStatus;

/*
 * Rest API 오류 발생 시 에러 내용을 응답한다.
 */
public interface ErrorRestResponse {
    
    /*
     * 전송할 HTTP 상태 코드
     */
    HttpStatus getStatus();

    /*
     * 자체 정의한 에러 코드 (숫자가 아닌 영문자)
     */
    String getCode();

    /*
     * 한글 에러 메시지 텍스트
     */
    String getMessage();
}
