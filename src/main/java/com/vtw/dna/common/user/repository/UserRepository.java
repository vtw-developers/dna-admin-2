package com.vtw.dna.common.user.repository;

import com.vtw.dna.common.user.dto.UserCommand;
import com.vtw.dna.common.user.dto.UserFilter;
import com.vtw.dna.common.user.dto.UserQuery;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Mapper
public interface UserRepository {
    int count(UserFilter filter, Pageable pageable);

    List<UserQuery> findAll(UserFilter filter, Pageable pageable);

    UserQuery findById(String id);

    void update(UserCommand user);

    void delete(String id);
}
