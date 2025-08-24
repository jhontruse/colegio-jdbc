package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Rol;
import com.colegio.colegio_jdbc.model.rowMapper.RolRowMapper;
import com.colegio.colegio_jdbc.repository.IRolRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RolRepository implements IRolRepository {

    private final JdbcTemplate jdbc;
    private static final RolRowMapper M = new RolRowMapper();

    @Override
    public Optional<Rol> findById(String id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject(
                    "SELECT id, nombre, descripcion FROM rol WHERE id=?", M, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Rol> findAll() {
        try {
            return jdbc.query("SELECT id,nombre,descripcion FROM rol ORDER BY nombre", M);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Rol r) {
        try {
            String id = Optional.ofNullable(r.getId()).filter(s -> !s.isBlank()).orElse(UUID.randomUUID().toString());
            jdbc.update("INSERT INTO rol(id,nombre,descripcion) VALUES(?,?,?)",
                    id, r.getNombre(), r.getDescripcion());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Rol r) {
        try {
            return jdbc.update("UPDATE rol SET nombre=?, descripcion=? WHERE id=?",
                    r.getNombre(), r.getDescripcion(), r.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM rol WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
