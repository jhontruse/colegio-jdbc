package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Menu;
import com.colegio.colegio_jdbc.model.rowMapper.MenuRowMapper;
import com.colegio.colegio_jdbc.repository.IMenuRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MenuRepository implements IMenuRepository {

    private final JdbcTemplate jdbc;
    private static final MenuRowMapper M = new MenuRowMapper();

    @Override
    public Optional<Menu> findById(String id) {
        try {
            var list = jdbc.query("SELECT * FROM menu WHERE id=?", M, id);
            return list.stream().findFirst();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Menu> findRoots() {
        try {
            return jdbc.query("SELECT * FROM menu WHERE parent_id IS NULL ORDER BY orden, nombre", M);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public List<Menu> findChildren(String parentId) {
        try {
            return jdbc.query("SELECT * FROM menu WHERE parent_id=? ORDER BY orden, nombre", M, parentId);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Menu m) {
        try {
            String id = Optional.ofNullable(m.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO menu(id,parent_id,nombre,codigo,ruta,icono,orden,visible,activo,created_at,updated_at)
                      VALUES(?,?,?,?,?,?,?,?,?,NOW(),NOW())
                    """, id, m.getParentId(), m.getNombre(), m.getCodigo(), m.getRuta(), m.getIcono(),
                    m.getOrden(), m.isVisible(), m.isActivo());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Menu m) {
        try {
            return jdbc.update(
                    """
                              UPDATE menu SET parent_id=?, nombre=?, codigo=?, ruta=?, icono=?, orden=?, visible=?, activo=?, updated_at=NOW()
                              WHERE id=?
                            """,
                    m.getParentId(), m.getNombre(), m.getCodigo(), m.getRuta(), m.getIcono(), m.getOrden(),
                    m.isVisible(), m.isActivo(), m.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM menu WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
