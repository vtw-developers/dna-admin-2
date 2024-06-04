package com.vtw.dna.integration.manage.api.repository;

import com.vtw.dna.integration.manage.api.dto.ApiInfoFilter;
import com.vtw.dna.integration.manage.api.HttpMethod;
import com.vtw.dna.integration.manage.api.dto.ApiInfoCommand;
import com.vtw.dna.integration.manage.api.dto.ApiInfoQuery;
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
