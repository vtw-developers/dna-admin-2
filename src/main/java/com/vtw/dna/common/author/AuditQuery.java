package com.vtw.dna.common.author;

import lombok.Data;

import java.time.LocalDateTime;

/*
 * 작성자 및 변경시각 정보
 */
@Data
public class AuditQuery {
    private String authorId;
    private String authorName;
    private LocalDateTime modifiedTime;
}
