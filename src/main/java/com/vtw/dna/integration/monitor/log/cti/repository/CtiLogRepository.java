package com.vtw.dna.integration.monitor.log.cti.repository;

import com.vtw.dna.integration.monitor.log.cti.dto.CtiLogFilter;
import com.vtw.dna.integration.monitor.log.cti.dto.CtiLogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CtiLogRepository {
    int count(CtiLogFilter filter, Pageable pageable);
    List<CtiLogQuery> findAll(CtiLogFilter filter, Pageable pageable);
    Optional<CtiLogQuery> findById(Long id);
}
