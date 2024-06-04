package com.vtw.dna.integration.monitor.log.cti.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.monitor.log.cti.dto.CtiLogFilter;
import com.vtw.dna.integration.monitor.log.cti.dto.CtiLogQuery;
import com.vtw.dna.integration.monitor.log.cti.service.CtiLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/log/cti")
public class CtiLogController {

    private final CtiLogService service;

    @GetMapping(value = "list")
    public Page<CtiLogQuery> list(CtiLogFilter filter, Pageable pageable) throws Exception {
        Page<CtiLogQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public CtiLogQuery find(@RequestParam Long id) throws Exception {
        CtiLogQuery entity = service.find(id);
        return entity;
    }

}
