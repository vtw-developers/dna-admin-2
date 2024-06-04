package com.vtw.dna.integration.monitor.stats.api.repository;

import com.vtw.dna.integration.monitor.stats.api.dto.ApiForStats;
import com.vtw.dna.integration.monitor.stats.api.dto.ApiStatsFilter;
import com.vtw.dna.integration.monitor.stats.api.dto.ApiStatsQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ApiStatsRepository {
    List<ApiStatsQuery> findAll(ApiStatsFilter filter);
    Optional<ApiStatsQuery> findById(Long id);

    List<ApiForStats> findAllApi();
}
