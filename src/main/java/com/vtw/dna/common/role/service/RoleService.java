package com.vtw.dna.common.role.service;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.common.role.dto.RoleQuery;
import com.vtw.dna.common.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository repository;

    public List<RoleQuery> list() throws Exception {
//        return repository.findAll().stream().filter(e -> e.getId()!= 3L).toList();
        return repository.findAll();
    }
}
