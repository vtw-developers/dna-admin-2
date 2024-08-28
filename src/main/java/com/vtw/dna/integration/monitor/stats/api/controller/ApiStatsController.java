package com.vtw.dna.integration.monitor.stats.api.controller;

import com.vtw.dna.integration.monitor.stats.api.dto.ApiStatsFilter;
import com.vtw.dna.integration.monitor.stats.api.dto.ApiStatsQuery;
import com.vtw.dna.integration.monitor.stats.api.service.ApiStatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stats")
public class ApiStatsController {

    private final ApiStatsService service;

    @GetMapping
    public List<ApiStatsQuery> stats(ApiStatsFilter filter) throws Exception {
        List<ApiStatsQuery> page = service.stats(filter);
        return page;
    }

}
