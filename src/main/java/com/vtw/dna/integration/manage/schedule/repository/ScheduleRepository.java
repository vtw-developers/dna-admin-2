package com.vtw.dna.integration.manage.schedule.repository;

import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleFilter;
import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleQuery;
import com.vtw.dna.integration.manage.schedule.dto.ScheduleCommand;
import com.vtw.dna.integration.manage.schedule.dto.ScheduleQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ScheduleRepository {
    int count(CtiScheduleFilter filter, Pageable pageable);
    List<ScheduleQuery> findAll(CtiScheduleFilter filter, Pageable pageable);
    Optional<ScheduleQuery> findById(Long id);
    int insert(ScheduleCommand scheduleCommand);
    int update(ScheduleCommand scheduleCommand);
}
