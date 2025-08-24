package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.ProfesorSeccion;
import com.colegio.colegio_jdbc.model.rowMapper.ProfesorSeccionRowMapper;
import com.colegio.colegio_jdbc.repository.IProfesorSeccionRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProfesorSeccionRepository implements IProfesorSeccionRepository {

    private final JdbcTemplate jdbc;
    private static final ProfesorSeccionRowMapper M = new ProfesorSeccionRowMapper();

    @Override
    public Optional<ProfesorSeccion> findById(String id) {
        try {
            var list = jdbc.query("SELECT * FROM profesor_seccion WHERE id=?", M, id);
            return list.stream().findFirst();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<ProfesorSeccion> findAllBySeccion(String seccionId) {
        try {
            return jdbc.query("SELECT * FROM profesor_seccion WHERE seccion_id=?", M, seccionId);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(ProfesorSeccion ps) {
        try {
            String id = Optional.ofNullable(ps.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("INSERT INTO profesor_seccion(id,seccion_id,profesor_id) VALUES(?,?,?)",
                    id, ps.getSeccionId(), ps.getProfesorId());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM profesor_seccion WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int deleteBySeccionAndProfesor(String seccionId, String profesorId) {
        try {
            return jdbc.update("DELETE FROM profesor_seccion WHERE seccion_id=? AND profesor_id=?", seccionId,
                    profesorId);
        } catch (Exception e) {
            return 0;
        }
    }

}
