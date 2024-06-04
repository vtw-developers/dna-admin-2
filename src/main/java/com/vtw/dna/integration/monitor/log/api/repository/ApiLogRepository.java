package com.vtw.dna.integration.monitor.log.api.repository;

import com.vtw.dna.integration.monitor.log.api.dto.ApiLogFilter;
import com.vtw.dna.integration.monitor.log.api.dto.ApiLogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ApiLogRepository {
    int count(ApiLogFilter filter, Pageable pageable);
    List<ApiLogQuery> findAll(ApiLogFilter filter, Pageable pageable);
    Optional<ApiLogQuery> findById(Long id);
}
