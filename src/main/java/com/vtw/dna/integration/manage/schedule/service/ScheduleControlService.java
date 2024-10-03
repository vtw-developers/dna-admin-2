package com.vtw.dna.integration.manage.schedule.service;

import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.manage.cti.repository.CtiInfoRepository;
import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleFilter;
import com.vtw.dna.integration.manage.schedule.dto.ScheduleCommand;
import com.vtw.dna.integration.manage.schedule.dto.ScheduleQuery;
import com.vtw.dna.integration.manage.schedule.quartz.QuartzScheduleService;
import com.vtw.dna.integration.manage.schedule.quartz.ScheduleView;
import com.vtw.dna.integration.manage.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleControlService {

    private final ScheduleRepository repository;
    private final QuartzScheduleService quartzScheduleService;

    public static final String APP_ID = "Central";

    public ScheduleView register(Long id) throws Exception {
        ScheduleQuery schedule = repository.findById(id).orElseThrow();
        String flowId = schedule.getFlowId();
        String cronExpr = schedule.getCronExpr();
        try {
            quartzScheduleService.create(APP_ID, flowId, cronExpr);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        ScheduleView view = quartzScheduleService.findByFlowId(flowId);
        return view;
    }

    public void start(Long id) throws Exception {
        ScheduleQuery schedule = repository.findById(id).orElseThrow();
        String flowId = schedule.getFlowId();
        quartzScheduleService.start(APP_ID, flowId);
    }

    public void stop(Long id) throws Exception {
        ScheduleQuery schedule = repository.findById(id).orElseThrow();
        String flowId = schedule.getFlowId();
        quartzScheduleService.stop(APP_ID, flowId);
    }

    public void run(Long id) throws Exception {
        ScheduleQuery schedule = repository.findById(id).orElseThrow();
        String flowId = schedule.getFlowId();
        quartzScheduleService.runOnce(APP_ID, flowId);
    }
}
