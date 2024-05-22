package com.vtw.dna.apiinfo.repository;

import com.vtw.dna.apiinfo.ApiInfo;
import com.vtw.dna.apiinfo.ApiInfoFilter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ApiInfoRepository {
    int count(ApiInfoFilter filter, Pageable pageable);
    List<ApiInfo> findAll(ApiInfoFilter filter, Pageable pageable);
    Optional<ApiInfo> findById(Long id);
    void insert(ApiInfo apiInfo);
    void update(ApiInfo apiInfo);
    void delete(ApiInfo apiInfo);
}
