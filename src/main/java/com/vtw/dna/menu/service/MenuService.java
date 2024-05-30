package com.vtw.dna.menu.service;

import com.vtw.dna.menu.Menu;
import com.vtw.dna.menu.MenuQuery;
import com.vtw.dna.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository repository;

    public List<Menu> list() {
        List<Menu> list = repository.findAll();
        return list;
    }

    public List<MenuQuery> view() {
        List<MenuQuery> view = repository.findViewAll();
        return view;
    }

    @Transactional
    public void save(List<Menu> menus) {
        repository.deleteAll();
        for (Menu menu : menus) {
            repository.insert(menu);
        }
    }
}
