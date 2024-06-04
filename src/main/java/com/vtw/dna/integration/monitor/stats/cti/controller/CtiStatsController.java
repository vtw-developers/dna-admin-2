package com.vtw.dna.integration.monitor.stats.cti.controller;

import com.vtw.dna.integration.monitor.stats.cti.dto.CtiStatsFilter;
import com.vtw.dna.integration.monitor.stats.cti.dto.CtiStatsQuery;
import com.vtw.dna.integration.monitor.stats.cti.service.CtiStatsService;
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
@RequestMapping("/monitor/stats/cti")
public class CtiStatsController {

    private final CtiStatsService service;

    @GetMapping(value = "list")
    public List<CtiStatsQuery> list(CtiStatsFilter filter) throws Exception {
        List<CtiStatsQuery> page = service.list(filter);
        return page;
    }

    @GetMapping(value = "find")
    public CtiStatsQuery find(@RequestParam Long id) throws Exception {
        CtiStatsQuery entity = service.find(id);
        return entity;
    }

}
