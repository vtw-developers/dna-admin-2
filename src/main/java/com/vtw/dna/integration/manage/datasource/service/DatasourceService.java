package com.vtw.dna.integration.manage.datasource.service;

import com.vtw.dna.common.exception.EntityAlreadyExistsException;
import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.manage.datasource.dto.DatasourceCommand;
import com.vtw.dna.integration.manage.datasource.dto.DatasourceFilter;
import com.vtw.dna.integration.manage.datasource.dto.DatasourceQuery;
import com.vtw.dna.integration.manage.datasource.repository.DatasourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DatasourceService {

    private final DatasourceRepository repository;

    public Page<DatasourceQuery> list(DatasourceFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<DatasourceQuery> list = repository.findAll(filter, pageable);
        Page<DatasourceQuery> page = Page.<DatasourceQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public List<DatasourceQuery> findAll() throws Exception {
        List<DatasourceQuery> list = repository.findAllWithoutPage();
        return list;
    }

    public DatasourceQuery find(Long id) throws Exception {
        DatasourceQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("ApiInfo", id));
        return entity;
    }

    public void create(DatasourceCommand entity) throws Exception {
        validate(entity);
        repository.insert(entity);
    }

    public void update(DatasourceCommand entity) throws Exception {
        find(entity.getId()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        validate(entity);
        repository.update(entity);
    }

    public void delete(DatasourceCommand entity) throws Exception {
        repository.delete(entity);
    }

    public void validate(DatasourceCommand entity) {
        boolean existsByName = existsByName(entity.getId(), entity.getName());
        if (existsByName) {
            throw new EntityAlreadyExistsException("Datasource", "name", entity.getName());
        }
    }

    public boolean existsByName(Long id, String name) {
        boolean exists = repository.existsByName(id, name);
        return exists;
    }

}
