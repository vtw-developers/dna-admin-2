package com.vtw.dna.servicegroup.service;

import com.vtw.dna.common.rest.EntityAlreadyExistsException;
import com.vtw.dna.common.rest.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.servicegroup.dto.ServiceGroupCommand;
import com.vtw.dna.servicegroup.dto.ServiceGroupFilter;
import com.vtw.dna.servicegroup.dto.ServiceGroupQuery;
import com.vtw.dna.servicegroup.repository.ServiceGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceGroupService {

    private final ServiceGroupRepository repository;

    public Page<ServiceGroupQuery> list(ServiceGroupFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<ServiceGroupQuery> list = repository.findAll(filter, pageable);
        Page<ServiceGroupQuery> page = Page.<ServiceGroupQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public ServiceGroupQuery find(Long id) throws Exception {
        ServiceGroupQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("ApiInfo", id));
        return entity;
    }

    public void create(ServiceGroupCommand entity) throws Exception {
        validate(entity);
        repository.insert(entity);
    }

    public void update(ServiceGroupCommand entity) throws Exception {
        find(entity.getId()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        validate(entity);
        repository.update(entity);
    }

    public void delete(ServiceGroupCommand entity) throws Exception {
        repository.delete(entity);
    }

    public void validate(ServiceGroupCommand entity) {
        boolean existsByName = existsByName(entity.getId(), entity.getName());
        if (existsByName) {
            throw new EntityAlreadyExistsException("ApiInfo", "name", entity.getName());
        }
    }

    public boolean existsByName(Long id, String name) {
        boolean exists = repository.existsByName(id, name);
        return exists;
    }

}
