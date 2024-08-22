package com.vtw.dna.common.menu.pageinfo.controller;

import com.vtw.dna.common.menu.pageinfo.PageInfo;
import com.vtw.dna.common.menu.pageinfo.PageInfoFilter;
import com.vtw.dna.common.menu.pageinfo.PageLevel;
import com.vtw.dna.common.menu.pageinfo.service.PageInfoService;
import com.vtw.dna.common.rest.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("pageInfo")
public class PageInfoController {

    private final PageInfoService service;

    @GetMapping(value = "list")
    public Page<PageInfo> pagingPageInfos(PageInfoFilter filter, Pageable pageable) {
        Page<PageInfo> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "findAll")
    public List<PageInfo> pageInfos() {
        List<PageInfo> page = service.findAll();
        return page;
    }

    @GetMapping(value = "find")
    public PageInfo pageInfo(@RequestParam Long id) {
        return service.find(id);
    }

    @GetMapping(value = "pageLevel")
    public PageLevel pageLevel(@RequestParam String path) {
        return service.findByPath(path);
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
