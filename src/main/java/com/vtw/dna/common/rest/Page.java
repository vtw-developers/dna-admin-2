package com.vtw.dna.common.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * 페이징된 목록 조회 요청에 응답하는 DTO
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Page<T> {
    private long totalCount;
    private List<T> data;
}
