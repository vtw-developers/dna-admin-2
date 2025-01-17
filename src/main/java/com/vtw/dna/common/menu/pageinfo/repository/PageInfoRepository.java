package com.vtw.dna.common.menu.pageinfo.repository;

import com.vtw.dna.common.menu.pageinfo.PageInfo;
import com.vtw.dna.common.menu.pageinfo.PageInfoFilter;
import com.vtw.dna.common.menu.pageinfo.PageLevel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PageInfoRepository {
    int count(PageInfoFilter filter, Pageable pageable);

    List<PageInfo> pagingList(PageInfoFilter filter, Pageable pageable);

    List<PageInfo> findAll();

    Optional<PageInfo> findById(Long id);

    PageLevel findByPath(String path);

    void insert(PageInfo pageInfo);

    void update(PageInfo pageInfo);

    void delete(PageInfo pageInfo);

    boolean existsByName(String name);

}
