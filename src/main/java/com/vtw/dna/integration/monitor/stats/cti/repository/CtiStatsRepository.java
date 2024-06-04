package com.vtw.dna.integration.monitor.stats.cti.repository;

import com.vtw.dna.integration.monitor.stats.cti.dto.CtiForStats;
import com.vtw.dna.integration.monitor.stats.cti.dto.CtiStatsFilter;
import com.vtw.dna.integration.monitor.stats.cti.dto.CtiStatsQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CtiStatsRepository {
    List<CtiStatsQuery> findAll(CtiStatsFilter filter);
    Optional<CtiStatsQuery> findById(Long id);

    List<CtiForStats> findAllApi();
}
