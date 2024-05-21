package com.vtw.dna.common.rest;

import lombok.Data;

/*
 * Rest API 성공 응답으로 딱히 응답할 데이터가 없을 때 사용한다.
 */
@Data
public class EmptySuccessResponse {
    private boolean success = true;
}
