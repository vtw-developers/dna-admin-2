package com.vtw.dna.monitor.log.service;

import com.vtw.dna.common.rest.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.monitor.log.dto.ApiLogFilter;
import com.vtw.dna.monitor.log.dto.ApiLogQuery;
import com.vtw.dna.monitor.log.repository.ApiLogRepository;
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
