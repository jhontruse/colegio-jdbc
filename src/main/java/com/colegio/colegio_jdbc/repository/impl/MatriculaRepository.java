package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Matricula;
import com.colegio.colegio_jdbc.model.rowMapper.MatriculaRowMapper;
import com.colegio.colegio_jdbc.repository.IMatriculaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MatriculaRepository implements IMatriculaRepository {

    private final JdbcTemplate jdbc;
    private static final MatriculaRowMapper M = new MatriculaRowMapper();

    @Override
    public Optional<Matricula> findById(String id) {
        try {
            var list = jdbc.query("SELECT * FROM matricula WHERE id=?", M, id);
            return list.stream().findFirst();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Matricula> findBySeccion(String seccionId) {
        try {
            return jdbc.query("SELECT * FROM matricula WHERE seccion_id=? ORDER BY fecha DESC", M, seccionId);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public List<Matricula> findByEstudiante(String estudianteId) {
        try {
            return jdbc.query("SELECT * FROM matricula WHERE estudiante_id=? ORDER BY fecha DESC", M, estudianteId);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Matricula m) {
        try {
            String id = Optional.ofNullable(m.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO matricula(id,estudiante_id,seccion_id,fecha,estado) VALUES(?,?,?,?,?)
                    """, id, m.getEstudianteId(), m.getSeccionId(), m.getFecha(), m.getEstado().name());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Matricula m) {
        try {
            return jdbc.update("UPDATE matricula SET estudiante_id=?, seccion_id=?, estado=? WHERE id=?",
                    m.getEstudianteId(), m.getSeccionId(), m.getEstado().name(), m.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM matricula WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
