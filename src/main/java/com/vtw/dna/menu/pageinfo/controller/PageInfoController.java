package com.vtw.dna.menu.pageinfo.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.menu.pageinfo.PageInfo;
import com.vtw.dna.menu.pageinfo.PageInfoFilter;
import com.vtw.dna.menu.pageinfo.service.PageInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("pageInfo")
public class PageInfoController {

    private final PageInfoService service;

    @GetMapping(value = "list")
    public Page<PageInfo> pagingPrograms(PageInfoFilter filter, Pageable pageable) {
        Page<PageInfo> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public PageInfo program(@RequestParam Long id) {
        return service.find(id);
    }

    @PostMapping(value = "create")
    public PageInfo create(@Valid @RequestBody PageInfo pageInfo) {
        service.create(pageInfo);
        return pageInfo;
    }

    @PostMapping(value = "update")
    public PageInfo update(@Valid @RequestBody PageInfo pageInfo) throws Exception {
        service.update(pageInfo);
        return pageInfo;
    }

    @PostMapping(value = "delete")
    public PageInfo delete(@Valid @RequestBody PageInfo pageInfo) {
        service.delete(pageInfo);
        return pageInfo;
    }

}
