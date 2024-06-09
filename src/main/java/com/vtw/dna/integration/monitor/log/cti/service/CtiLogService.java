package com.vtw.dna.integration.monitor.log.cti.service;

import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.monitor.log.cti.dto.CtiLogFilter;
import com.vtw.dna.integration.monitor.log.cti.dto.CtiLogQuery;
import com.vtw.dna.integration.monitor.log.cti.repository.CtiLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CtiLogService {

    private final CtiLogRepository repository;

    public Page<CtiLogQuery> list(CtiLogFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<CtiLogQuery> list = repository.findAll(filter, pageable);
        Page<CtiLogQuery> page = Page.<CtiLogQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public CtiLogQuery find(Long id) throws Exception {
        CtiLogQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("CtiInfo", id));
        return entity;
    }

}
