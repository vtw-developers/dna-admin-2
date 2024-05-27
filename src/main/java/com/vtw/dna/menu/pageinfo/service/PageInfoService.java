package com.vtw.dna.menu.pageinfo.service;

import com.vtw.dna.common.rest.EntityAlreadyExistsException;
import com.vtw.dna.common.rest.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.menu.pageinfo.PageInfo;
import com.vtw.dna.menu.pageinfo.PageInfoFilter;
import com.vtw.dna.menu.pageinfo.repository.PageInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PageInfoService {

    private final PageInfoRepository repository;

    public Page<PageInfo> list(PageInfoFilter filter, Pageable pageable) {
        int count = repository.count(filter, pageable);
        List<PageInfo> list = repository.findAll(filter, pageable);
        Page<PageInfo> page = Page.<PageInfo>builder().totalCount(count).data(list).build();
        return page;
    }

    public PageInfo find(Long id) {
        PageInfo pageInfo = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("PageInfo", id));
        return pageInfo;
    }

    public void create(PageInfo pageInfo) {
        validate(pageInfo);
        repository.insert(pageInfo);
    }

    public void update(PageInfo entity) throws Exception {
        find(entity.getId()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        validate(entity);
        repository.update(entity);
    }

    public void delete(PageInfo entity) {
        repository.delete(entity);
    }

    public void validate(PageInfo entity) {
        boolean existsByName = existsByName(entity.getName());
        if (existsByName) {
            throw new EntityAlreadyExistsException("PageInfo", "name", entity.getName());
        }
    }

    public boolean existsByName(String name) {
        boolean exists = repository.existsByName(name);
        return exists;
    }

}
