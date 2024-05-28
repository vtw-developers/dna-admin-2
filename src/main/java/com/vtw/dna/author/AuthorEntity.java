package com.vtw.dna.author;

import com.vtw.dna.login.dto.LoginUser;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

/**
 * 작성자와 수정일시를 입력하는 엔티티
 */
@Data
public class AuthorEntity {
    private Author author;
    private LocalDateTime modifiedTime;

    public void authorFromLoginUser() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.setAuthor(Author.builder().id(loginUser.getId()).build());
    }
}
