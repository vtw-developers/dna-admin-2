package com.vtw.dna.author;

import com.vtw.dna.login.dto.LoginUser;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class AuthorEntity {
    private Author author;

    public void authorFromLoginUser() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.setAuthor(Author.builder().id(loginUser.getId()).build());
    }
}
