package com.vtw.dna.menu.repository;

import com.vtw.dna.menu.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuRepository {
    List<Menu> findAll();

    void insert(Menu menu);

    void deleteAll();
}
