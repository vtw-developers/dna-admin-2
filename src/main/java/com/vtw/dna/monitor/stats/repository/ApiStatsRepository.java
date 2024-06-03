package com.vtw.dna.monitor.stats.repository;

import com.vtw.dna.monitor.stats.dto.ApiForStats;
import com.vtw.dna.monitor.stats.dto.ApiStatsFilter;
import com.vtw.dna.monitor.stats.dto.ApiStatsQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ApiStatsRepository {
    List<ApiStatsQuery> findAll(ApiStatsFilter filter);
    Optional<ApiStatsQuery> findById(Long id);

    List<ApiForStats> findAllApi();
}
