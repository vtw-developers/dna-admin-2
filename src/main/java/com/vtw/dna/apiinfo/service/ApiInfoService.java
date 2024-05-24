package com.vtw.dna.apiinfo.service;

import com.vtw.dna.apiinfo.ApiInfo;
import com.vtw.dna.apiinfo.ApiInfoFilter;
import com.vtw.dna.apiinfo.HttpMethod;
import com.vtw.dna.common.rest.EntityAlreadyExistsException;
import com.vtw.dna.common.rest.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.apiinfo.repository.ApiInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApiInfoService {

    private final ApiInfoRepository apiInfoRepository;

    public Page<ApiInfo> list(ApiInfoFilter filter, Pageable pageable) throws Exception {
        int count = apiInfoRepository.count(filter, pageable);
        List<ApiInfo> list = apiInfoRepository.findAll(filter, pageable);
        Page<ApiInfo> page = Page.<ApiInfo>builder().totalCount(count).data(list).build();
        return page;
    }

    public ApiInfo find(Long id) throws Exception {
        ApiInfo entity = apiInfoRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("ApiInfo", id));
        return entity;
    }

    public void create(ApiInfo entity) throws Exception {
        validate(entity);
        apiInfoRepository.insert(entity);
    }

    public void update(ApiInfo entity) throws Exception {
        find(entity.getId()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        validate(entity);
        apiInfoRepository.update(entity);
    }

    public void delete(ApiInfo entity) throws Exception {
        apiInfoRepository.delete(entity);
    }

    public void validate(ApiInfo entity) {
        boolean exists = exists(entity.getName());
        if (exists) {
            throw new EntityAlreadyExistsException("ApiInfo", "name", entity.getName());
        }
    }

    public boolean exists(String name) {
        boolean exists = apiInfoRepository.existsByName(name);
        return exists;
    }

}
