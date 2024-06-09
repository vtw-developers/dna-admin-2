package com.vtw.dna.integration.monitor.log.api.service;

import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.monitor.log.api.dto.ApiLogFilter;
import com.vtw.dna.integration.monitor.log.api.dto.ApiLogQuery;
import com.vtw.dna.integration.monitor.log.api.repository.ApiLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApiLogService {

    private final ApiLogRepository repository;

    public Page<ApiLogQuery> list(ApiLogFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<ApiLogQuery> list = repository.findAll(filter, pageable);
        Page<ApiLogQuery> page = Page.<ApiLogQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public ApiLogQuery find(Long id) throws Exception {
        ApiLogQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("ApiInfo", id));
        return entity;
    }

}
