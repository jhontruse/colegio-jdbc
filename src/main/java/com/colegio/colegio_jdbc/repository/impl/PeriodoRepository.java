package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Periodo;
import com.colegio.colegio_jdbc.model.rowMapper.PeriodoRowMapper;
import com.colegio.colegio_jdbc.repository.IPeriodoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PeriodoRepository implements IPeriodoRepository {

    private final JdbcTemplate jdbc;
    private static final PeriodoRowMapper M = new PeriodoRowMapper();

    @Override
    public Optional<Periodo> findById(String id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("SELECT * FROM periodo WHERE id=?", M, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Periodo> findAll() {
        try {
            return jdbc.query("SELECT * FROM periodo ORDER BY fecha_inicio DESC", M);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String create(Periodo p) {
        try {
            String id = Optional.ofNullable(p.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO periodo(id,nombre,anio,fecha_inicio,fecha_fin,activo) VALUES(?,?,?,?,?,?)
                    """, id, p.getNombre(), p.getAnio(), p.getFechaInicio(), p.getFechaFin(), p.isActivo());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Periodo p) {
        try {
            return jdbc.update("""
                      UPDATE periodo SET nombre=?, anio=?, fecha_inicio=?, fecha_fin=?, activo=? WHERE id=?
                    """, p.getNombre(), p.getAnio(), p.getFechaInicio(), p.getFechaFin(), p.isActivo(), p.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM periodo WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
