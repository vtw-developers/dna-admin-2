package com.vtw.dna.common.board.board.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vtw.dna.common.author.SignInAuthor;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BoardFile implements SignInAuthor {
    private Long id;

    private Long boardId;

    private String originalFileName;

    private String storeFileName;

    private String filePath;

    private boolean useYn;
}
