package com.vtw.dna.apiinfo.service;

import com.vtw.dna.apiinfo.dto.ApiInfoFilter;
import com.vtw.dna.apiinfo.HttpMethod;
import com.vtw.dna.apiinfo.dto.ApiInfoCommand;
import com.vtw.dna.apiinfo.dto.ApiInfoQuery;
import com.vtw.dna.apiinfo.repository.ApiInfoRepository;
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

    private final ApiInfoRepository apiInfoRepository;

    public Page<ApiInfoQuery> list(ApiInfoFilter filter, Pageable pageable) throws Exception {
        int count = apiInfoRepository.count(filter, pageable);
        List<ApiInfoQuery> list = apiInfoRepository.findAll(filter, pageable);
        Page<ApiInfoQuery> page = Page.<ApiInfoQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public ApiInfoQuery find(Long id) throws Exception {
        ApiInfoQuery entity = apiInfoRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("ApiInfo", id));
        return entity;
    }

    public void create(ApiInfoCommand entity) throws Exception {
        validate(entity);
        apiInfoRepository.insert(entity);
    }

    public void update(ApiInfoCommand entity) throws Exception {
        find(entity.getId()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        validate(entity);
        apiInfoRepository.update(entity);
    }

    public void delete(ApiInfoCommand entity) throws Exception {
        apiInfoRepository.delete(entity);
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
        boolean exists = apiInfoRepository.existsByName(id, name);
        return exists;
    }

    public boolean existsByEndpoint(Long id, HttpMethod httpMethod, String url) {
        boolean exists = apiInfoRepository.existsByHttpMethodAndUrl(id, httpMethod, url);
        return exists;
    }

}
