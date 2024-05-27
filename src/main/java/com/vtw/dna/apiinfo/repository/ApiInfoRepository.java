package com.vtw.dna.apiinfo.repository;

import com.vtw.dna.apiinfo.ApiInfo;
import com.vtw.dna.apiinfo.ApiInfoFilter;
import com.vtw.dna.apiinfo.HttpMethod;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ApiInfoRepository {
    int count(ApiInfoFilter filter, Pageable pageable);
    List<ApiInfo> findAll(ApiInfoFilter filter, Pageable pageable);
    Optional<ApiInfo> findById(Long id);
    int insert(ApiInfo apiInfo);
    int update(ApiInfo apiInfo);
    int delete(ApiInfo apiInfo);

    boolean existsByName(Long id, String name);
    boolean existsByHttpMethodAndUrl(Long id, HttpMethod httpMethod, String url);
}
