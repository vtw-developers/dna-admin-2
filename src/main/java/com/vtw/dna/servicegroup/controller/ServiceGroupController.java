package com.vtw.dna.servicegroup.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.servicegroup.dto.ServiceGroupCommand;
import com.vtw.dna.servicegroup.dto.ServiceGroupFilter;
import com.vtw.dna.servicegroup.dto.ServiceGroupQuery;
import com.vtw.dna.servicegroup.service.ServiceGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("serviceGroup")
public class ServiceGroupController {

    private final ServiceGroupService service;

    @GetMapping(value = "list")
    public Page<ServiceGroupQuery> list(ServiceGroupFilter filter, Pageable pageable) throws Exception {
        Page<ServiceGroupQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public ServiceGroupQuery find(@RequestParam Long id) throws Exception {
        ServiceGroupQuery entity = service.find(id);
        return entity;
    }

    @PostMapping(value = "create")
    public ServiceGroupCommand create(@Valid @RequestBody ServiceGroupCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public ServiceGroupCommand update(@Valid @RequestBody ServiceGroupCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

    @PostMapping(value = "delete")
    public ServiceGroupCommand delete(@Valid @RequestBody ServiceGroupCommand entity) throws Exception {
        service.delete(entity);
        return entity;
    }

}
