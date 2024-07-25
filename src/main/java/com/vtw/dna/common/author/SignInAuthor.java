package com.vtw.dna.common.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtw.dna.common.auth.dto.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;

public interface SignInAuthor {
    @JsonIgnore
    default String getAuthorId() {
        AuthUser signInUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return signInUser.getId();
    }
}
