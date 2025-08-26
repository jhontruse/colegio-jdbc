package com.colegio.colegio_jdbc.repository;

import java.util.List;

import com.colegio.colegio_jdbc.model.entity.RolMenu;

public interface IRolMenuRepository {

    List<RolMenu> findAllByRol(String rolId);

    int add(String rolId, String menuId);

    int remove(String rolId, String menuId);

    int removeAllByRol(String rolId);

}
