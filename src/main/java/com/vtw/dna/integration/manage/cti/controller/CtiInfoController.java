package com.vtw.dna.integration.manage.cti.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.manage.cti.dto.CtiInfoCommand;
import com.vtw.dna.integration.manage.cti.dto.CtiInfoFilter;
import com.vtw.dna.integration.manage.cti.dto.CtiInfoQuery;
import com.vtw.dna.integration.manage.cti.service.CtiInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("ctiInfo")
public class CtiInfoController {

    private final CtiInfoService service;

    @GetMapping(value = "list")
    public Page<CtiInfoQuery> list(CtiInfoFilter filter, Pageable pageable) throws Exception {
        Page<CtiInfoQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public CtiInfoQuery find(@RequestParam Long id) throws Exception {
        CtiInfoQuery entity = service.find(id);
        return entity;
    }

    @PostMapping(value = "create")
    public CtiInfoCommand create(@Valid @RequestBody CtiInfoCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public CtiInfoCommand update(@Valid @RequestBody CtiInfoCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

    @PostMapping(value = "delete")
    public CtiInfoCommand delete(@Valid @RequestBody CtiInfoCommand entity) throws Exception {
        service.delete(entity);
        return entity;
    }

}
