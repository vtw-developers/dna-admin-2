package com.vtw.dna.common.role.controller;

import com.vtw.dna.common.role.dto.RoleQuery;
import com.vtw.dna.common.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleService service;

    @GetMapping(value = "list")
    public List<RoleQuery> list() throws Exception {
        return service.list();
    }
}
