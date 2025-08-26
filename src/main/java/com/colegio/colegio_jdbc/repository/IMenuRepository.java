package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Menu;

public interface IMenuRepository {

    Optional<Menu> findById(String id);

    List<Menu> findRoots();

    List<Menu> findChildren(String parentId);

    String create(Menu m);

    int update(Menu m);

    int delete(String id);

}
