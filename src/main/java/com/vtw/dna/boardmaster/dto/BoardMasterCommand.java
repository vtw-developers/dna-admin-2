package com.vtw.dna.boardmaster.dto;

import com.vtw.dna.author.LoginUserAuthor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BoardMasterCommand implements LoginUserAuthor {
    private Long id;

    @Size(max = 100)
    @NotBlank
    private String name;

    private String instruction;

    private String code;

    private Boolean fileAttachYn;

    private Long fileCount;

    private String fileMaxSize;

    private Boolean replyYn;

    private Boolean useYn;

}
