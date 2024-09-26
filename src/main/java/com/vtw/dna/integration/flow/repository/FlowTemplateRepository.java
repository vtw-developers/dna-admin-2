package com.vtw.dna.integration.flow.repository;

import com.vtw.dna.integration.flow.dto.FlowTemplateCommand;
import com.vtw.dna.integration.flow.dto.FlowTemplateFilter;
import com.vtw.dna.integration.flow.dto.FlowTemplateQuery;
import com.vtw.dna.integration.flow.dto.TemplatedFlowQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FlowTemplateRepository {
    int count(FlowTemplateFilter filter, Pageable pageable);
    List<FlowTemplateQuery> list(FlowTemplateFilter filter, Pageable pageable);
    List<FlowTemplateQuery> findAll();
    Optional<FlowTemplateQuery> findById(Long sid);
    Optional<FlowTemplateQuery> findByTemplateId(String templateId);
    int insert(FlowTemplateCommand apiInfo);
    int update(FlowTemplateCommand apiInfo);
    int delete(FlowTemplateCommand apiInfo);

    boolean existsByName(Long sid, String name);
}
