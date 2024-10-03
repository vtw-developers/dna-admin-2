package com.vtw.dna.integration.manage.schedule.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleFilter;
import com.vtw.dna.integration.manage.schedule.dto.ScheduleCommand;
import com.vtw.dna.integration.manage.schedule.dto.ScheduleQuery;
import com.vtw.dna.integration.manage.schedule.quartz.ScheduleView;
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
    public ScheduleQuery register(@RequestBody ScheduleCommand command) throws Exception {
        Long id = command.getId();
        ScheduleQuery query = service.register(id);
        return query;
    }

    @PostMapping(value = "start")
    public ScheduleQuery start(@RequestBody ScheduleCommand command) throws Exception {
        Long id = command.getId();
        ScheduleQuery query = service.start(id);
        return query;
    }

    @PostMapping(value = "stop")
    public ScheduleQuery stop(@RequestBody ScheduleCommand command) throws Exception {
        Long id = command.getId();
        ScheduleQuery query = service.stop(id);
        return query;
    }

    @PostMapping(value = "run")
    public ScheduleQuery run(@RequestBody ScheduleCommand command) throws Exception {
        Long id = command.getId();
        ScheduleQuery query = service.run(id);
        return query;
    }

}
