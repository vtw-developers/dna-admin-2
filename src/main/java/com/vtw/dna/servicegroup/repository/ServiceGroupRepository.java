package com.vtw.dna.servicegroup.repository;

import com.vtw.dna.apiinfo.HttpMethod;
import com.vtw.dna.servicegroup.dto.ServiceGroupCommand;
import com.vtw.dna.servicegroup.dto.ServiceGroupFilter;
import com.vtw.dna.servicegroup.dto.ServiceGroupQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ServiceGroupRepository {
    int count(ServiceGroupFilter filter, Pageable pageable);
    List<ServiceGroupQuery> findAll(ServiceGroupFilter filter, Pageable pageable);
    Optional<ServiceGroupQuery> findById(Long id);
    int insert(ServiceGroupCommand apiInfo);
    int update(ServiceGroupCommand apiInfo);
    int delete(ServiceGroupCommand apiInfo);

    boolean existsByName(Long id, String name);
}
