package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Evaluacion;
import com.colegio.colegio_jdbc.model.rowMapper.EvaluacionRowMapper;
import com.colegio.colegio_jdbc.repository.IEvaluacionRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EvaluacionRepository implements IEvaluacionRepository {

    private final JdbcTemplate jdbc;
    private static final EvaluacionRowMapper M = new EvaluacionRowMapper();

    @Override
    public Optional<Evaluacion> findById(String id) {
        try {
            var list = jdbc.query("SELECT * FROM evaluacion WHERE id=?", M, id);
            return list.stream().findFirst();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Evaluacion> findBySeccion(String seccionId) {
        try {
            return jdbc.query("SELECT * FROM evaluacion WHERE seccion_id=? ORDER BY fecha IS NULL, fecha", M,
                    seccionId);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Evaluacion e) {
        try {
            String id = Optional.ofNullable(e.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO evaluacion(id,seccion_id,nombre,ponderacion,fecha)
                      VALUES(?,?,?,?,?)
                    """, id, e.getSeccionId(), e.getNombre(), e.getPonderacion(), e.getFecha());
            return id;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public int update(Evaluacion e) {
        try {
            return jdbc.update("UPDATE evaluacion SET nombre=?, ponderacion=?, fecha=? WHERE id=?",
                    e.getNombre(), e.getPonderacion(), e.getFecha(), e.getId());
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM evaluacion WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
