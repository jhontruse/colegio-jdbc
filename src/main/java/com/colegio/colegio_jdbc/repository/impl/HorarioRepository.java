package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Horario;
import com.colegio.colegio_jdbc.model.rowMapper.HorarioRowMapper;
import com.colegio.colegio_jdbc.repository.IHorarioRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HorarioRepository implements IHorarioRepository {

    private final JdbcTemplate jdbc;
    private static final HorarioRowMapper M = new HorarioRowMapper();

    @Override
    public Optional<Horario> findById(String id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("SELECT * FROM horario WHERE id=?", M, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Horario> findBySeccion(String seccionId) {
        try {
            return jdbc.query("SELECT * FROM horario WHERE seccion_id=? ORDER BY dia_semana,hora_inicio", M, seccionId);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Horario h) {
        try {
            String id = Optional.ofNullable(h.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO horario(id,seccion_id,dia_semana,hora_inicio,hora_fin,aula_id) VALUES(?,?,?,?,?,?)
                    """, id, h.getSeccionId(), h.getDiaSemana(), h.getHoraInicio(), h.getHoraFin(), h.getAulaId());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Horario h) {
        try {
            return jdbc.update("""
                      UPDATE horario SET seccion_id=?, dia_semana=?, hora_inicio=?, hora_fin=?, aula_id=? WHERE id=?
                    """, h.getSeccionId(), h.getDiaSemana(), h.getHoraInicio(), h.getHoraFin(), h.getAulaId(),
                    h.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM horario WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
