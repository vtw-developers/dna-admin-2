package com.vtw.dna.apiinfo.repository;

import com.vtw.dna.apiinfo.dto.ApiInfoFilter;
import com.vtw.dna.apiinfo.HttpMethod;
import com.vtw.dna.apiinfo.dto.ApiInfoCommand;
import com.vtw.dna.apiinfo.dto.ApiInfoQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ApiInfoRepository {
    int count(ApiInfoFilter filter, Pageable pageable);
    List<ApiInfoQuery> findAll(ApiInfoFilter filter, Pageable pageable);
    Optional<ApiInfoQuery> findById(Long id);
    int insert(ApiInfoCommand apiInfo);
    int update(ApiInfoCommand apiInfo);
    int delete(ApiInfoCommand apiInfo);

    boolean existsByName(Long id, String name);
    boolean existsByHttpMethodAndUrl(Long id, HttpMethod httpMethod, String url);
}
