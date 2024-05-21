package com.vtw.dna.login.repository;

import com.vtw.dna.login.dto.LoginVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginRepository {
    LoginVO selectByIdAndPassword(String id, String password);
}
