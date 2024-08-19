package com.vtw.dna.common.menu.service;

import com.vtw.dna.common.auth.dto.AuthUser;
import com.vtw.dna.common.menu.Menu;
import com.vtw.dna.common.menu.MenuQuery;
import com.vtw.dna.common.menu.repository.MenuRepository;
import com.vtw.dna.common.user.dto.UserQuery;
import com.vtw.dna.common.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository repository;
    private final UserRepository userRepository;

    public List<MenuQuery> list() {
        List<MenuQuery> view = repository.findViewAll();
        return view;
    }

    public List<MenuQuery> view() {
        AuthUser signInUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserQuery userInfo = userRepository.findById(signInUser.getId());
        Long roleLevel = userInfo.getRoleLevel();
        if (roleLevel == null) {
            roleLevel = 3L;
        }
        List<MenuQuery> view = repository.findViewByRoleLevel(roleLevel);
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
