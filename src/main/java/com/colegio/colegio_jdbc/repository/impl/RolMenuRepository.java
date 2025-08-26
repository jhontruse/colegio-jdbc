package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.RolMenu;
import com.colegio.colegio_jdbc.model.rowMapper.RolMenuRowMapper;
import com.colegio.colegio_jdbc.repository.IRolMenuRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RolMenuRepository implements IRolMenuRepository {

    private final JdbcTemplate jdbc;
    private static final RolMenuRowMapper M = new RolMenuRowMapper();

    @Override
    public List<RolMenu> findAllByRol(String rolId) {
        try {
            return jdbc.query("SELECT rol_id,menu_id FROM rol_menu WHERE rol_id=?", M, rolId);
        } catch (EmptyResultDataAccessException e) {
            return List.of();
        }
    }

    @Override
    public int add(String rolId, String menuId) {
        try {
            return jdbc.update("INSERT INTO rol_menu(rol_id,menu_id) VALUES(?,?)", rolId, menuId);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int remove(String rolId, String menuId) {
        try {
            return jdbc.update("DELETE FROM rol_menu WHERE rol_id=? AND menu_id=?", rolId, menuId);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int removeAllByRol(String rolId) {
        try {
            return jdbc.update("DELETE FROM rol_menu WHERE rol_id=?", rolId);
        } catch (Exception e) {
            return 0;
        }
    }

}
