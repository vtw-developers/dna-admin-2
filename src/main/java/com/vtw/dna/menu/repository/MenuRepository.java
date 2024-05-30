package com.vtw.dna.menu.repository;

import com.vtw.dna.menu.Menu;
import com.vtw.dna.menu.MenuQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuRepository {
    List<Menu> findAll();

    List<MenuQuery> findViewAll();

    void insert(Menu menu);

    void deleteAll();
}
