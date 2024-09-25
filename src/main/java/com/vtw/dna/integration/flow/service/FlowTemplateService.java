package com.vtw.dna.integration.flow.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.vtw.dna.common.exception.EntityAlreadyExistsException;
import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.flow.dto.*;
import com.vtw.dna.integration.flow.repository.FlowTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlowTemplateService {

    private final FlowTemplateRepository repository;

    public Page<FlowTemplateQuery> list(FlowTemplateFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<FlowTemplateQuery> list = repository.list(filter, pageable);
        Page<FlowTemplateQuery> page = Page.<FlowTemplateQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public FlowTemplateQuery find(Long sid) throws Exception {
        FlowTemplateQuery entity = repository.findById(sid).orElseThrow(() -> new NoSuchEntityException("FlowTemplate", sid));
        return entity;
    }

    public List<FlowTemplateQuery> findAll() throws Exception {
        List<FlowTemplateQuery> list = repository.findAll();
        return list;
    }

    public void create(FlowTemplateCommand entity) throws Exception {
        validate(entity);
        repository.insert(entity);
    }

    public void update(FlowTemplateCommand entity) throws Exception {
        find(entity.getSid()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        validate(entity);
        repository.update(entity);
    }

    public void delete(FlowTemplateCommand entity) throws Exception {
        repository.delete(entity);
    }

    public void validate(FlowTemplateCommand entity) {
        boolean existsByName = existsByName(entity.getSid(), entity.getName());
        if (existsByName) {
            throw new EntityAlreadyExistsException("ApiInfo", "name", entity.getName());
        }
    }

    public boolean existsByName(Long sid, String name) {
        boolean exists = repository.existsByName(sid, name);
        return exists;
    }

    public FlowTemplateQuery importFlowTemplate(String yaml) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        FlowTemplateMeta meta = objectMapper.readValue(yaml, FlowTemplateMeta.class);
        FlowTemplateQuery query = meta.convert();
        return query;
    }
}
