package com.vtw.dna.common.role.repository;

import com.vtw.dna.common.role.dto.RoleQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleRepository {
    List<RoleQuery> findAll();
}
