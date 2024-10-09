package com.vtw.dna.integration.flow.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.vtw.dna.common.exception.EntityAlreadyExistsException;
import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.flow.dto.*;
import com.vtw.dna.integration.flow.repository.FlowTemplateRepository;
import com.vtw.dna.integration.flow.repository.FlowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlowService {

    private final FlowRepository repository;
    private final FlowTemplateRepository flowTemplateRepository;

    public Page<FlowQuery> list(FlowFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<FlowQuery> list = repository.list(filter, pageable);
        Page<FlowQuery> page = Page.<FlowQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public FlowQuery find(Long id) throws Exception {
        FlowQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("TemplatedFlow", id));
        return entity;
    }

    public void create(FlowCommand entity) throws Exception {
        validate(entity);
        repository.insert(entity);
    }

    public void update(FlowCommand entity) throws Exception {
        find(entity.getSid()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        validate(entity);
        repository.update(entity);
    }

    public void delete(FlowCommand entity) throws Exception {
        repository.delete(entity);
    }

    public void validate(FlowCommand entity) {
        boolean existsByName = existsByName(entity.getSid(), entity.getName());
        if (existsByName) {
            throw new EntityAlreadyExistsException("ApiInfo", "name", entity.getName());
        }
    }

    public boolean existsByName(Long id, String name) {
        boolean exists = repository.existsByName(id, name);
        return exists;
    }

    public FlowQuery importFlow(String yaml) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        FlowMeta meta = objectMapper.readValue(yaml, FlowMeta.class);
        FlowQuery convert = meta.convert(flowTemplateRepository);
        return convert;
    }

    public String exportFlow(DataSchemaView dataSchemaView) throws Exception {
        DataSchema dataSchema = dataSchemaView.convert();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory().enable(YAMLGenerator.Feature.MINIMIZE_QUOTES));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String yaml = objectMapper.writeValueAsString(dataSchema);
        return yaml;
    }

    public List<FlowQuery> getSchedulableFlows() {
        List<FlowQuery> schedulableFlows = repository.getSchedulableFlows();
        return schedulableFlows;
    }
}
