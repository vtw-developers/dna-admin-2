package com.vtw.dna.author;

import com.vtw.dna.common.auth.dto.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;

public interface SignInAuthor {
    default String getAuthorId() {
        AuthUser signInUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return signInUser.getId();
    }
}
