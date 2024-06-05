package com.vtw.dna.board.dto;


import com.vtw.dna.author.SignInAuthor;
import lombok.Data;

@Data
public class BoardFile implements SignInAuthor {
    private Long id;

    private Long boardId;

    private String originalFileName;

    private String storeFileName;

    private String filePath;

    private boolean useYn;
}
