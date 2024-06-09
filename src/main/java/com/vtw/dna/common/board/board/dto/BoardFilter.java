package com.vtw.dna.common.board.board.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class BoardFilter {
    private Long id;
    private Long boardMasterId;
    private String title;
    private String author;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startModifiedTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endModifiedTime;
}
