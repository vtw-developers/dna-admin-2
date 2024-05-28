package com.vtw.dna.author;

import com.vtw.dna.login.dto.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

public interface LoginUserAuthor {
    default String getAuthorId() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginUser.getId();
    }
}
