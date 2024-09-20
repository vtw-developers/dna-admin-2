package com.vtw.dna.integration.flow.repository;

import com.vtw.dna.integration.flow.dto.TemplatedFlowCommand;
import com.vtw.dna.integration.flow.dto.TemplatedFlowFilter;
import com.vtw.dna.integration.flow.dto.TemplatedFlowQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TemplatedFlowRepository {
    int count(TemplatedFlowFilter filter, Pageable pageable);
    List<TemplatedFlowQuery> list(TemplatedFlowFilter filter, Pageable pageable);
    List<TemplatedFlowQuery> findAll();
    Optional<TemplatedFlowQuery> findById(Long id);
    int insert(TemplatedFlowCommand apiInfo);
    int update(TemplatedFlowCommand apiInfo);
    int delete(TemplatedFlowCommand apiInfo);

    boolean existsByName(Long id, String name);
}