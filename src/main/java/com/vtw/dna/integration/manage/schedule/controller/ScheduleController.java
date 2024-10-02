package com.vtw.dna.integration.manage.schedule.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.manage.schedule.dto.*;
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
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping(value = "list")
    public Page<ScheduleQuery> list(CtiScheduleFilter filter, Pageable pageable) throws Exception {
        Page<ScheduleQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public ScheduleQuery find(@RequestParam Long id) throws Exception {
        ScheduleQuery entity = service.find(id);
        return entity;
    }

    @PostMapping(value = "create")
    public ScheduleCommand create(@Valid @RequestBody ScheduleCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

}
