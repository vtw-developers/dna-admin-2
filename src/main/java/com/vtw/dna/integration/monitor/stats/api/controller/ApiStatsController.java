package com.vtw.dna.integration.monitor.stats.api.controller;

import com.vtw.dna.integration.monitor.stats.api.dto.ApiStatsFilter;
import com.vtw.dna.integration.monitor.stats.api.dto.ApiStatsQuery;
import com.vtw.dna.integration.monitor.stats.api.service.ApiStatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/stats/api")
public class ApiStatsController {

    private final ApiStatsService service;

    @GetMapping(value = "list")
    public List<ApiStatsQuery> list(ApiStatsFilter filter) throws Exception {
        List<ApiStatsQuery> page = service.list(filter);
        return page;
    }

    @GetMapping(value = "find")
    public ApiStatsQuery find(@RequestParam Long id) throws Exception {
        ApiStatsQuery entity = service.find(id);
        return entity;
    }

}
