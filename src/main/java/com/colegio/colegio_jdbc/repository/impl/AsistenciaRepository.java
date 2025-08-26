package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Asistencia;
import com.colegio.colegio_jdbc.model.rowMapper.AsistenciaRowMapper;
import com.colegio.colegio_jdbc.repository.IAsistenciaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AsistenciaRepository implements IAsistenciaRepository {

    private final JdbcTemplate jdbc;
    private static final AsistenciaRowMapper M = new AsistenciaRowMapper();

    @Override
    public Optional<Asistencia> findById(String id) {
        try {
            var list = jdbc.query("SELECT * FROM asistencia WHERE id=?", M, id);
            return list.stream().findFirst();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Asistencia> findByClase(String claseId) {
        try {
            return jdbc.query("SELECT * FROM asistencia WHERE clase_id=? ORDER BY marcada_at DESC", M, claseId);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Asistencia a) {
        try {
            String id = Optional.ofNullable(a.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO asistencia(id,clase_id,estudiante_id,estado,justificacion,marcada_at)
                      VALUES(?,?,?,?,?,?)
                    """, id, a.getClaseId(), a.getEstudianteId(), a.getEstado().name(), a.getJustificacion(),
                    a.getMarcadaAt());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Asistencia a) {
        try {
            return jdbc.update("UPDATE asistencia SET estado=?, justificacion=? WHERE id=?",
                    a.getEstado().name(), a.getJustificacion(), a.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM asistencia WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
