package com.vtw.dna.integration.manage.api.controller;

import com.vtw.dna.integration.manage.api.dto.ApiInfoFilter;
import com.vtw.dna.integration.manage.api.dto.ApiInfoCommand;
import com.vtw.dna.integration.manage.api.dto.ApiInfoQuery;
import com.vtw.dna.integration.manage.api.service.ApiInfoService;
import com.vtw.dna.common.rest.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("apiInfo")
public class ApiInfoController {

    private final ApiInfoService service;

    @GetMapping(value = "list")
    public Page<ApiInfoQuery> list(ApiInfoFilter filter, Pageable pageable) throws Exception {
        Page<ApiInfoQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public ApiInfoQuery find(@RequestParam Long id) throws Exception {
        ApiInfoQuery entity = service.find(id);
        return entity;
    }

    @PostMapping(value = "create")
    public ApiInfoCommand create(@Valid @RequestBody ApiInfoCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public ApiInfoCommand update(@Valid @RequestBody ApiInfoCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

    @PostMapping(value = "delete")
    public ApiInfoCommand delete(@Valid @RequestBody ApiInfoCommand entity) throws Exception {
        service.delete(entity);
        return entity;
    }

}
