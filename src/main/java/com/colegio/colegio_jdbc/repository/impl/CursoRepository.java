package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Curso;
import com.colegio.colegio_jdbc.model.rowMapper.CursoRowMapper;
import com.colegio.colegio_jdbc.repository.ICursoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CursoRepository implements ICursoRepository {

    private final JdbcTemplate jdbc;
    private static final CursoRowMapper M = new CursoRowMapper();

    @Override
    public Optional<Curso> findById(String id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("SELECT * FROM curso WHERE id=?", M, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Curso> findAll() {
        try {
            return jdbc.query("SELECT * FROM curso ORDER BY nombre", M);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Curso c) {
        try {
            String id = Optional.ofNullable(c.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO curso(id,codigo,nombre,creditos,horas_semanales,nivel) VALUES(?,?,?,?,?,?)
                    """, id, c.getCodigo(), c.getNombre(), c.getCreditos(), c.getHorasSemanales(), c.getNivel());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Curso c) {
        try {
            return jdbc.update("""
                      UPDATE curso SET codigo=?, nombre=?, creditos=?, horas_semanales=?, nivel=? WHERE id=?
                    """, c.getCodigo(), c.getNombre(), c.getCreditos(), c.getHorasSemanales(), c.getNivel(), c.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM curso WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
