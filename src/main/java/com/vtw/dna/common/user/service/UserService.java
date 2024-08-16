package com.vtw.dna.common.user.service;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.common.user.dto.UserCommand;
import com.vtw.dna.common.user.dto.UserFilter;
import com.vtw.dna.common.user.dto.UserQuery;
import com.vtw.dna.common.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public Page<UserQuery> list(UserFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<UserQuery> userList = repository.findAll(filter, pageable);
        return Page.<UserQuery>builder().totalCount(count).data(userList).build();
    }

    public UserQuery find(String id) throws Exception {
        return repository.findById(id);
    }

    public UserCommand update(UserCommand user) throws Exception {
        repository.update(user);
        return user;
    }

    public String delete(String id) throws Exception {
        repository.delete(id);
        return id;
    }
}
