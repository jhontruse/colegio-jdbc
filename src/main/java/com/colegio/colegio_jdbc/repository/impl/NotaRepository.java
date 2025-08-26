package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Nota;
import com.colegio.colegio_jdbc.model.rowMapper.NotaRowMapper;
import com.colegio.colegio_jdbc.repository.INotaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NotaRepository implements INotaRepository {

    private final JdbcTemplate jdbc;
    private static final NotaRowMapper M = new NotaRowMapper();

    @Override
    public Optional<Nota> findById(String id) {
        try {
            var list = jdbc.query("SELECT * FROM nota WHERE id=?", M, id);
            return list.stream().findFirst();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Nota> findByEvaluacion(String evaluacionId) {
        try {
            return jdbc.query("SELECT * FROM nota WHERE evaluacion_id=? ORDER BY fecha_registro DESC", M, evaluacionId);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Nota n) {
        try {
            String id = Optional.ofNullable(n.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO nota(id,evaluacion_id,estudiante_id,valor,fecha_registro)
                      VALUES(?,?,?,?,?)
                    """, id, n.getEvaluacionId(), n.getEstudianteId(), n.getValor(), n.getFechaRegistro());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Nota n) {
        try {
            return jdbc.update("UPDATE nota SET valor=? WHERE id=?", n.getValor(), n.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM nota WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
