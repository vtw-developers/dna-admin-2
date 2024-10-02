package com.vtw.dna.integration.manage.schedule.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleFilter;
import com.vtw.dna.integration.manage.schedule.dto.ScheduleCommand;
import com.vtw.dna.integration.manage.schedule.dto.ScheduleQuery;
import com.vtw.dna.integration.manage.schedule.service.ScheduleControlService;
import com.vtw.dna.integration.manage.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/manage/schedule")
public class ScheduleControlController {

    private final ScheduleControlService service;

    @PostMapping(value = "register")
    public ScheduleCommand register(ScheduleCommand command) throws Exception {
        Long id = command.getFlowSid();
        service.register(id);
        return command;
    }

    @PostMapping(value = "start")
    public ScheduleCommand start(ScheduleCommand command) throws Exception {
        Long id = command.getFlowSid();
        service.start(id);
        return command;
    }

    @PostMapping(value = "stop")
    public ScheduleCommand stop(ScheduleCommand command) throws Exception {
        Long id = command.getFlowSid();
        service.stop(id);
        return command;
    }

    @PostMapping(value = "run")
    public ScheduleCommand run(ScheduleCommand command) throws Exception {
        Long id = command.getFlowSid();
        service.run(id);
        return command;
    }

}
