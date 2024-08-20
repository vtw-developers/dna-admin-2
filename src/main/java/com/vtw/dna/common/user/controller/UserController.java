package com.vtw.dna.common.user.controller;

import com.vtw.dna.common.rest.Page;
import com.vtw.dna.common.user.dto.UserCommand;
import com.vtw.dna.common.user.dto.UserFilter;
import com.vtw.dna.common.user.dto.UserQuery;
import com.vtw.dna.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService service;

    @GetMapping(value = "list")
    public Page<UserQuery> list(UserFilter filter, Pageable pageable) throws Exception {
        return service.list(filter, pageable);
    }

    @GetMapping(value = "find")
    public UserQuery find(@RequestParam String id) throws Exception {
        return service.find(id);
    }


    @PostMapping(value = "create")
    public UserCommand create(@Valid @RequestBody UserCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public UserCommand update(@Valid @RequestBody UserCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

    @GetMapping(value = "delete")
    public String delete(@RequestParam String id) throws Exception {
        return service.delete(id);
    }

    @GetMapping(value = "approval")
    public String approval(@RequestParam String id) throws Exception {
        service.approval(id);
        return id;
    }

}
