package com.vtw.dna.integration.flow.repository;

import com.vtw.dna.integration.flow.dto.FlowCommand;
import com.vtw.dna.integration.flow.dto.FlowFilter;
import com.vtw.dna.integration.flow.dto.FlowQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FlowRepository {
    int count(FlowFilter filter, Pageable pageable);
    List<FlowQuery> list(FlowFilter filter, Pageable pageable);
    List<FlowQuery> findAll();
    Optional<FlowQuery> findById(Long sid);
    int insert(FlowCommand flow);
    int update(FlowCommand flow);
    int delete(FlowCommand flow);

    boolean existsByName(Long sid, String name);

    List<FlowQuery> getSchedulableFlows();
}
