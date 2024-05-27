package com.vtw.dna.login.repository;

import com.vtw.dna.login.dto.LoginUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginRepository {
    LoginUser selectByIdAndPassword(String id, String password);
}
