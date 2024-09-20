package com.vtw.dna.integration.flow.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.flow.dto.TemplatedFlowCommand;
import com.vtw.dna.integration.flow.dto.TemplatedFlowFilter;
import com.vtw.dna.integration.flow.dto.TemplatedFlowQuery;
import com.vtw.dna.integration.flow.service.TemplatedFlowService;
import com.vtw.dna.integration.flow.service.TemplatedFlowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("templatedFlow")
public class TemplatedFlowController {

    private final TemplatedFlowService service;

    @GetMapping(value = "list")
    public Page<TemplatedFlowQuery> list(TemplatedFlowFilter filter, Pageable pageable) throws Exception {
        Page<TemplatedFlowQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public TemplatedFlowQuery find(@RequestParam Long id) throws Exception {
        TemplatedFlowQuery entity = service.find(id);
        return entity;
    }

    @PostMapping(value = "create")
    public TemplatedFlowCommand create(@Valid @RequestBody TemplatedFlowCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public TemplatedFlowCommand update(@Valid @RequestBody TemplatedFlowCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

    @PostMapping(value = "delete")
    public TemplatedFlowCommand delete(@Valid @RequestBody TemplatedFlowCommand entity) throws Exception {
        service.delete(entity);
        return entity;
    }

}
