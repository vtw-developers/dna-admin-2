package com.vtw.dna.integration.monitor.log.api.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.monitor.log.api.dto.ApiLogFilter;
import com.vtw.dna.integration.monitor.log.api.dto.ApiLogQuery;
import com.vtw.dna.integration.monitor.log.api.service.ApiLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/log/api")
public class ApiLogController {

    private final ApiLogService service;

    @GetMapping(value = "list")
    public Page<ApiLogQuery> list(ApiLogFilter filter, Pageable pageable) throws Exception {
        Page<ApiLogQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public ApiLogQuery find(@RequestParam Long id) throws Exception {
        ApiLogQuery entity = service.find(id);
        return entity;
    }

}
