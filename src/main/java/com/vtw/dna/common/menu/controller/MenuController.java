package com.vtw.dna.common.menu.controller;

import com.vtw.dna.common.menu.Menu;
import com.vtw.dna.common.menu.MenuQuery;
import com.vtw.dna.common.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuService service;

    @GetMapping(value = "list")
    public List<Menu> menuList() {
        List<Menu> list = service.list();
        return list;
    }

    @GetMapping(value = "view")
    public List<MenuQuery> menuView() {
        List<MenuQuery> list = service.view();
        return list;
    }

    @PostMapping(value = "save")
    public void save(@Valid @RequestBody List<Menu> menus) {
        service.save(menus);
    }
}
