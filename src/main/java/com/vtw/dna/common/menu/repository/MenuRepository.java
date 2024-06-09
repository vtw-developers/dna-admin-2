package com.vtw.dna.common.menu.repository;

import com.vtw.dna.common.menu.Menu;
import com.vtw.dna.common.menu.MenuQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuRepository {
    List<Menu> findAll();

    List<MenuQuery> findViewAll();

    void insert(Menu menu);

    void deleteAll();
}
