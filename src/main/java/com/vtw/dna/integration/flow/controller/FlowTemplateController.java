package com.vtw.dna.integration.flow.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.flow.dto.FlowTemplateCommand;
import com.vtw.dna.integration.flow.dto.FlowTemplateFilter;
import com.vtw.dna.integration.flow.dto.FlowTemplateQuery;
import com.vtw.dna.integration.flow.dto.TemplatedFlowQuery;
import com.vtw.dna.integration.flow.service.FlowTemplateService;
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
@RequestMapping("flowTemplate")
public class FlowTemplateController {

    private final FlowTemplateService service;

    @GetMapping(value = "list")
    public Page<FlowTemplateQuery> list(FlowTemplateFilter filter, Pageable pageable) throws Exception {
        Page<FlowTemplateQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public FlowTemplateQuery find(@RequestParam Long sid) throws Exception {
        FlowTemplateQuery entity = service.find(sid);
        return entity;
    }

    @GetMapping(value = "findAll")
    public List<FlowTemplateQuery> findAll() throws Exception {
        List<FlowTemplateQuery> list = service.findAll();
        return list;
    }

    @PostMapping(value = "create")
    public FlowTemplateCommand create(@Valid @RequestBody FlowTemplateCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public FlowTemplateCommand update(@Valid @RequestBody FlowTemplateCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

    @PostMapping(value = "delete")
    public FlowTemplateCommand delete(@Valid @RequestBody FlowTemplateCommand entity) throws Exception {
        service.delete(entity);
        return entity;
    }

    @PostMapping(value = "import")
    public FlowTemplateQuery importFlowTemplate(@RequestBody Map<String, Object> map) throws Exception {
        String yaml = (String) map.get("yaml");
        FlowTemplateQuery flowTemplateQuery = service.importFlowTemplate(yaml);
        return flowTemplateQuery;
    }

}
