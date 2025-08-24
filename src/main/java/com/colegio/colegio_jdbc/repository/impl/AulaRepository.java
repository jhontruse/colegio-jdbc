package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Aula;
import com.colegio.colegio_jdbc.model.rowMapper.AulaRowMapper;
import com.colegio.colegio_jdbc.repository.IAulaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AulaRepository implements IAulaRepository {

    private final JdbcTemplate jdbc;
    private static final AulaRowMapper M = new AulaRowMapper();

    @Override
    public Optional<Aula> findById(String id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("SELECT * FROM aula WHERE id=?", M, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Aula> findAll() {
        try {
            return jdbc.query("SELECT * FROM aula ORDER BY codigo", M);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Aula a) {
        try {
            String id = Optional.ofNullable(a.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("INSERT INTO aula(id,codigo,aforo,ubicacion) VALUES(?,?,?,?)",
                    id, a.getCodigo(), a.getAforo(), a.getUbicacion());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Aula a) {
        try {
            return jdbc.update("UPDATE aula SET codigo=?, aforo=?, ubicacion=? WHERE id=?",
                    a.getCodigo(), a.getAforo(), a.getUbicacion(), a.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM aula WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
