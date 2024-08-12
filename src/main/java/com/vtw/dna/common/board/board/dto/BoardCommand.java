package com.vtw.dna.common.board.board.dto;

import com.vtw.dna.common.author.SignInAuthor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BoardCommand implements SignInAuthor {
    private Long id;

    private Long boardMasterId;

    @Size(max = 100)
    @NotBlank
    private String title;

    private String content;

    private Long boardNo;

    private Long parentId;

    private Long viewCount;

    private Boolean useYn;

    private Boolean pinYn;

    private Boolean popupYn;

    private LocalDateTime pinStartTime;

    private LocalDateTime pinEndTime;

    private LocalDateTime popupStartTime;

    private LocalDateTime popupEndTime;

    private List<BoardFile> removedFiles = new ArrayList<>();

}
