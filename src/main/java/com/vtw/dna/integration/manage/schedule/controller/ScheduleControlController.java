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
    public ScheduleView register(@RequestBody ScheduleCommand command) throws Exception {
        Long id = command.getId();
        ScheduleView view = service.register(id);
        return view;
    }

    @PostMapping(value = "start")
    public ScheduleCommand start(@RequestBody ScheduleCommand command) throws Exception {
        Long id = command.getId();
        service.start(id);
        return command;
    }

    @PostMapping(value = "stop")
    public ScheduleCommand stop(@RequestBody ScheduleCommand command) throws Exception {
        Long id = command.getId();
        service.stop(id);
        return command;
    }

    @PostMapping(value = "run")
    public ScheduleCommand run(@RequestBody ScheduleCommand command) throws Exception {
        Long id = command.getId();
        service.run(id);
        return command;
    }

}
