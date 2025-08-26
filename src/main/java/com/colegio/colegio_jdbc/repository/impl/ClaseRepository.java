package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Clase;
import com.colegio.colegio_jdbc.model.rowMapper.ClaseRowMapper;
import com.colegio.colegio_jdbc.repository.IClaseRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ClaseRepository implements IClaseRepository {

    private final JdbcTemplate jdbc;
    private static final ClaseRowMapper M = new ClaseRowMapper();

    @Override
    public Optional<Clase> findById(String id) {
        try {
            var list = jdbc.query("SELECT * FROM clase WHERE id=?", M, id);
            return list.stream().findFirst();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Clase> findBySeccion(String seccionId) {
        try {
            return jdbc.query("SELECT * FROM clase WHERE seccion_id=? ORDER BY nro_sesion", M, seccionId);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Clase c) {
        try {
            String id = Optional.ofNullable(c.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("INSERT INTO clase(id,seccion_id,fecha,nro_sesion,tema) VALUES(?,?,?,?,?)",
                    id, c.getSeccionId(), c.getFecha(), c.getNroSesion(), c.getTema());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Clase c) {
        try {
            return jdbc.update("UPDATE clase SET fecha=?, nro_sesion=?, tema=? WHERE id=?",
                    c.getFecha(), c.getNroSesion(), c.getTema(), c.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM clase WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
