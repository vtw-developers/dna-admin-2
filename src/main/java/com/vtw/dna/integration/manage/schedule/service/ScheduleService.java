package com.vtw.dna.integration.manage.schedule.service;

import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.manage.cti.repository.CtiInfoRepository;
import com.vtw.dna.integration.manage.schedule.dto.*;
import com.vtw.dna.integration.manage.schedule.quartz.QuartzScheduleService;
import com.vtw.dna.integration.manage.schedule.quartz.ScheduleView;
import com.vtw.dna.integration.manage.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CtiInfoRepository infoRepository;
    private final QuartzScheduleService quartzScheduleService;

    public Page<ScheduleQuery> list(CtiScheduleFilter filter, Pageable pageable) throws Exception {
        int count = scheduleRepository.count(filter, pageable);
        List<ScheduleQuery> list = scheduleRepository.findAll(filter, pageable);
        Page<ScheduleQuery> page = Page.<ScheduleQuery>builder().totalCount(count).data(list).build();


        for (ScheduleQuery scheduleQuery : list) {
            List<ScheduleView> quartzSchedules = quartzScheduleService.findAll();
            ScheduleView scheduleView = quartzSchedules.stream().filter(sched -> sched.getFlowId().equals(scheduleQuery.getFlowId())).findFirst().orElse(null);
            if (scheduleView != null) {
                String status = scheduleView.getStatus();
                scheduleQuery.setStatus(status);
            } else {
                scheduleQuery.setStatus("UNREGISTERED");
            }
        }

        return page;
    }

    public ScheduleQuery find(Long id) throws Exception {
        ScheduleQuery entity = scheduleRepository.findByCtiInfoId(id).orElseThrow(() -> new NoSuchEntityException("CtiInfo", id));
        return entity;
    }

    public void create(ScheduleCommand schedule) throws Exception {
        scheduleRepository.insert(schedule);
    }
}
