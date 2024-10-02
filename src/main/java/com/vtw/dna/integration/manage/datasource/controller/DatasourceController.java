package com.vtw.dna.integration.manage.datasource.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.manage.datasource.dto.DatasourceCommand;
import com.vtw.dna.integration.manage.datasource.dto.DatasourceFilter;
import com.vtw.dna.integration.manage.datasource.dto.DatasourceQuery;
import com.vtw.dna.integration.manage.datasource.service.DatasourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("datasource")
public class DatasourceController {

    private final DatasourceService service;

    @GetMapping(value = "list")
    public Page<DatasourceQuery> list(DatasourceFilter filter, Pageable pageable) throws Exception {
        Page<DatasourceQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public DatasourceQuery find(@RequestParam Long id) throws Exception {
        DatasourceQuery entity = service.find(id);
        return entity;
    }

    @PostMapping(value = "create")
    public DatasourceCommand create(@Valid @RequestBody DatasourceCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public DatasourceCommand update(@Valid @RequestBody DatasourceCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

    @PostMapping(value = "delete")
    public DatasourceCommand delete(@Valid @RequestBody DatasourceCommand entity) throws Exception {
        service.delete(entity);
        return entity;
    }

}
