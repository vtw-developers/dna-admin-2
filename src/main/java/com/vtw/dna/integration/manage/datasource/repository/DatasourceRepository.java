package com.vtw.dna.integration.manage.datasource.repository;

import com.vtw.dna.integration.manage.datasource.dto.DatasourceCommand;
import com.vtw.dna.integration.manage.datasource.dto.DatasourceFilter;
import com.vtw.dna.integration.manage.datasource.dto.DatasourceQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DatasourceRepository {
    int count(DatasourceFilter filter, Pageable pageable);
    List<DatasourceQuery> findAll(DatasourceFilter filter, Pageable pageable);
    List<DatasourceQuery> findAllWithoutPage();
    Optional<DatasourceQuery> findById(Long id);
    int insert(DatasourceCommand apiInfo);
    int update(DatasourceCommand apiInfo);
    int delete(DatasourceCommand apiInfo);

    boolean existsByName(Long id, String name);
}
