package com.vtw.dna.integration.flow.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.flow.dto.DataSchemaView;
import com.vtw.dna.integration.flow.dto.FlowCommand;
import com.vtw.dna.integration.flow.dto.FlowFilter;
import com.vtw.dna.integration.flow.dto.FlowQuery;
import com.vtw.dna.integration.flow.service.FlowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("flow")
public class FlowController {

    private final FlowService service;

    @GetMapping(value = "list")
    public Page<FlowQuery> list(FlowFilter filter, Pageable pageable) throws Exception {
        Page<FlowQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public FlowQuery find(@RequestParam Long sid) throws Exception {
        FlowQuery entity = service.find(sid);
        return entity;
    }

    @PostMapping(value = "create")
    public FlowCommand create(@Valid @RequestBody FlowCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public FlowCommand update(@Valid @RequestBody FlowCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

    @PostMapping(value = "delete")
    public FlowCommand delete(@Valid @RequestBody FlowCommand entity) throws Exception {
        service.delete(entity);
        return entity;
    }

    @PostMapping(value = "import")
    public FlowQuery importFlow(@RequestBody Map<String, Object> map) throws Exception {
        String yaml = (String) map.get("yaml");
        FlowQuery query = service.importFlow(yaml);
        return query;
    }

    @PostMapping(value = "export")
    public Map<String, Object> exportFlow(@Valid @RequestBody DataSchemaView dataSchemaView) throws Exception {
        String yaml = service.exportFlow(dataSchemaView);
        return Map.of("yaml", yaml);
    }

    @GetMapping(value = "getSchedulableFlows")
    public List<FlowQuery> getSchedulableFlows() throws Exception {
        List<FlowQuery> schedulableFlows = service.getSchedulableFlows();
        return schedulableFlows;
    }
}

