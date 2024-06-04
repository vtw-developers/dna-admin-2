package com.vtw.dna.integration.manage.api.service;

import com.vtw.dna.integration.manage.api.dto.ApiInfoFilter;
import com.vtw.dna.integration.manage.api.HttpMethod;
import com.vtw.dna.integration.manage.api.dto.ApiInfoCommand;
import com.vtw.dna.integration.manage.api.dto.ApiInfoQuery;
import com.vtw.dna.integration.manage.api.repository.ApiInfoRepository;
import com.vtw.dna.common.rest.EntityAlreadyExistsException;
import com.vtw.dna.common.rest.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApiInfoService {

    private final ApiInfoRepository repository;

    public Page<ApiInfoQuery> list(ApiInfoFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<ApiInfoQuery> list = repository.findAll(filter, pageable);
        Page<ApiInfoQuery> page = Page.<ApiInfoQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public ApiInfoQuery find(Long id) throws Exception {
        ApiInfoQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("ApiInfo", id));
        return entity;
    }

    public void create(ApiInfoCommand entity) throws Exception {
        validate(entity);
        repository.insert(entity);
    }

    public void update(ApiInfoCommand entity) throws Exception {
        find(entity.getId()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        validate(entity);
        repository.update(entity);
    }

    public void delete(ApiInfoCommand entity) throws Exception {
        repository.delete(entity);
    }

    public void validate(ApiInfoCommand entity) {
        boolean existsByName = existsByName(entity.getId(), entity.getName());
        if (existsByName) {
            throw new EntityAlreadyExistsException("ApiInfo", "name", entity.getName());
        }

        boolean existsByEndpoint = existsByEndpoint(entity.getId(), entity.getHttpMethod(), entity.getUrl());
        if (existsByEndpoint) {
            throw new EntityAlreadyExistsException("ApiInfo", "endpoint", entity.getHttpMethod() + ";" + entity.getUrl());
        }
    }

    public boolean existsByName(Long id, String name) {
        boolean exists = repository.existsByName(id, name);
        return exists;
    }

    public boolean existsByEndpoint(Long id, HttpMethod httpMethod, String url) {
        boolean exists = repository.existsByHttpMethodAndUrl(id, httpMethod, url);
        return exists;
    }

}
