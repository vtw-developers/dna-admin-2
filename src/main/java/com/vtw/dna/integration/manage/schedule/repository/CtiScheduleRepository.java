package com.vtw.dna.integration.manage.schedule.repository;

import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleFilter;
import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CtiScheduleRepository {
    int count(CtiScheduleFilter filter, Pageable pageable);
    List<CtiScheduleQuery> findAll(CtiScheduleFilter filter, Pageable pageable);
    Optional<CtiScheduleQuery> findById(Long id);
    Optional<CtiScheduleQuery> findByCtiInfoId(Long ctiInfoId);
    int insert(Long ctiInfoId, String cronExpr);
    int update(Long scheduleId, String cronExpr);
}
