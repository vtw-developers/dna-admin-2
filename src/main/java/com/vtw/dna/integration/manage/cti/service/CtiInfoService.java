package com.vtw.dna.integration.manage.cti.service;

import com.vtw.dna.common.exception.EntityAlreadyExistsException;
import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.integration.manage.cti.dto.CtiInfoCommand;
import com.vtw.dna.integration.manage.cti.dto.CtiInfoFilter;
import com.vtw.dna.integration.manage.cti.dto.CtiInfoQuery;
import com.vtw.dna.integration.manage.cti.repository.CtiInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CtiInfoService {

    private final CtiInfoRepository repository;

    public Page<CtiInfoQuery> list(CtiInfoFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<CtiInfoQuery> list = repository.findAll(filter, pageable);
        Page<CtiInfoQuery> page = Page.<CtiInfoQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public CtiInfoQuery find(Long id) throws Exception {
        CtiInfoQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("ApiInfo", id));
        return entity;
    }

    public void create(CtiInfoCommand entity) throws Exception {
        validate(entity);
        repository.insert(entity);
    }

    public void update(CtiInfoCommand entity) throws Exception {
        find(entity.getId()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        validate(entity);
        repository.update(entity);
    }

    public void delete(CtiInfoCommand entity) throws Exception {
        repository.delete(entity);
    }

    public void validate(CtiInfoCommand entity) {
        boolean existsByName = existsByName(entity.getId(), entity.getName());
        if (existsByName) {
            throw new EntityAlreadyExistsException("CtiInfo", "name", entity.getName());
        }
    }

    public boolean existsByName(Long id, String name) {
        boolean exists = repository.existsByName(id, name);
        return exists;
    }
}
