package com.vtw.dna.board.dto;

import com.vtw.dna.author.LoginUserAuthor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BoardCommand implements LoginUserAuthor {
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

}
