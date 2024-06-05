package com.vtw.dna.common.auth.repository;

import com.vtw.dna.common.auth.dto.AuthUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthRepository {
    AuthUser selectByIdAndPassword(String id, String password);

    void insert(AuthUser loginUser);
}
