package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Seccion;
import com.colegio.colegio_jdbc.model.rowMapper.SeccionRowMapper;
import com.colegio.colegio_jdbc.repository.ISeccionRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeccionRepository implements ISeccionRepository {

    private final JdbcTemplate jdbc;
    private static final SeccionRowMapper M = new SeccionRowMapper();

    @Override
    public Optional<Seccion> findById(String id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("SELECT * FROM seccion WHERE id=?", M, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Seccion> findAll() {
        try {
            return jdbc.query("SELECT * FROM seccion ORDER BY created_at DESC", M);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Seccion s) {
        try {
            String id = Optional.ofNullable(s.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO seccion(id,curso_id,periodo_id,codigo,capacidad,estado) VALUES(?,?,?,?,?,?)
                    """, id, s.getCursoId(), s.getPeriodoId(), s.getCodigo(), s.getCapacidad(), s.getEstado().name());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Seccion s) {
        try {
            return jdbc.update("""
                      UPDATE seccion SET curso_id=?, periodo_id=?, codigo=?, capacidad=?, estado=? WHERE id=?
                    """, s.getCursoId(), s.getPeriodoId(), s.getCodigo(), s.getCapacidad(), s.getEstado().name(),
                    s.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM seccion WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
