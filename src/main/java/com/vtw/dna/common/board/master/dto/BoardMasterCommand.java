package com.vtw.dna.common.board.master.dto;

import com.vtw.dna.common.author.SignInAuthor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BoardMasterCommand implements SignInAuthor {
    private Long id;

    @Size(max = 100)
    @NotBlank
    private String name;

    private String instruction;

    private Boolean fileAttachYn;

    private Long fileCount;

    private String fileMaxSize;

    private Boolean replyYn;

    private Boolean useYn;

    private Boolean commentYn;

    private Boolean pinYn;

    private Boolean popupYn;

}
