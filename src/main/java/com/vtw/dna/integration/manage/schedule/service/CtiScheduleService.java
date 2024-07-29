package com.vtw.dna.integration.manage.schedule.service;

import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.manage.api.dto.ApiInfoQuery;
import com.vtw.dna.integration.manage.cti.dto.CtiInfoQuery;
import com.vtw.dna.integration.manage.cti.repository.CtiInfoRepository;
import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleCommand;
import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleFilter;
import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleQuery;
import com.vtw.dna.integration.manage.schedule.repository.CtiScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CtiScheduleService {

    private final CtiScheduleRepository scheduleRepository;
    private final CtiInfoRepository infoRepository;

    public Page<CtiScheduleQuery> list(CtiScheduleFilter filter, Pageable pageable) throws Exception {
        int count = scheduleRepository.count(filter, pageable);
        List<CtiScheduleQuery> list = scheduleRepository.findAll(filter, pageable);
        Page<CtiScheduleQuery> page = Page.<CtiScheduleQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public CtiScheduleQuery find(Long id) throws Exception {
        CtiScheduleQuery entity = scheduleRepository.findByCtiInfoId(id).orElseThrow(() -> new NoSuchEntityException("CtiInfo", id));
        return entity;
    }

    public void update(CtiScheduleCommand schedule) throws Exception {
        Long ctiInfoId = schedule.getCtiInfoId();
        CtiInfoQuery ctiInfo = infoRepository.findById(ctiInfoId).orElseThrow(() -> new NoSuchEntityException("CtiInfo", ctiInfoId));
        CtiScheduleQuery oldSchedule = scheduleRepository.findByCtiInfoId(ctiInfo.getId()).orElse(null);
        if (oldSchedule == null) {
            scheduleRepository.insert(ctiInfo.getId(), schedule.getCronExpr());
        } else {
            scheduleRepository.update(oldSchedule.getId(), schedule.getCronExpr());
        }
    }
}
