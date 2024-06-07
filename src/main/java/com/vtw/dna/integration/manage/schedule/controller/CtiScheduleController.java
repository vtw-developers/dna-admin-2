package com.vtw.dna.integration.manage.schedule.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleCommand;
import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleFilter;
import com.vtw.dna.integration.manage.schedule.dto.CtiScheduleQuery;
import com.vtw.dna.integration.manage.schedule.service.CtiScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/manage/schedule")
public class CtiScheduleController {

    private final CtiScheduleService service;

    @GetMapping(value = "list")
    public Page<CtiScheduleQuery> list(CtiScheduleFilter filter, Pageable pageable) throws Exception {
        Page<CtiScheduleQuery> page = service.list(filter, pageable);
        return page;
    }

    @PostMapping(value = "update")
    public CtiScheduleCommand update(@Valid @RequestBody CtiScheduleCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

}
