package com.vtw.dna.boardmaster.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class BoardMasterFilter {
    private String name;
    private String author;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startModifiedTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endModifiedTime;

    private Boolean useYn;
    private Boolean replyYn;
    private Boolean fileAttachYn;
}
