package com.vtw.dna.apiinfo.controller;

import com.vtw.dna.apiinfo.ApiInfo;
import com.vtw.dna.apiinfo.ApiInfoFilter;
import com.vtw.dna.apiinfo.service.ApiInfoService;
import com.vtw.dna.common.rest.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BooleanTypeHandler;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("apiInfo")
public class ApiInfoController {

    private final ApiInfoService service;

    @GetMapping(value = "list")
    public Page<ApiInfo> list(ApiInfoFilter filter, Pageable pageable) throws Exception {
        Page<ApiInfo> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public ApiInfo find(@RequestParam Long id) throws Exception {
        ApiInfo entity = service.find(id);
        return entity;
    }

    @PostMapping(value = "create")
    public ApiInfo create(@Valid @RequestBody ApiInfo entity) throws Exception {
        entity.authorFromLoginUser();
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public ApiInfo update(@Valid @RequestBody ApiInfo entity) throws Exception {
        entity.authorFromLoginUser();
        service.update(entity);
        return entity;
    }

    @PostMapping(value = "delete")
    public ApiInfo delete(@Valid @RequestBody ApiInfo entity) throws Exception {
        service.delete(entity);
        return entity;
    }

}
