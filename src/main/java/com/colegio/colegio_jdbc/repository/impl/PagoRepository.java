package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Pago;
import com.colegio.colegio_jdbc.model.rowMapper.PagoRowMapper;
import com.colegio.colegio_jdbc.repository.IPagoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PagoRepository implements IPagoRepository {

    private final JdbcTemplate jdbc;
    private static final PagoRowMapper M = new PagoRowMapper();

    @Override
    public Optional<Pago> findById(String id) {
        try {
            var list = jdbc.query("SELECT * FROM pago WHERE id=?", M, id);
            return list.stream().findFirst();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Pago> findByPersonaPeriodo(String personaId, String periodoId) {
        try {
            return jdbc.query("SELECT * FROM pago WHERE persona_id=? AND periodo_id=? ORDER BY fecha_venc", M,
                    personaId, periodoId);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Pago p) {
        try {
            String id = Optional.ofNullable(p.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO pago(id,persona_id,periodo_id,concepto,monto,estado,fecha_venc,pagado_at,created_at)
                      VALUES(?,?,?,?,?,?,?,?,?)
                    """, id, p.getPersonaId(), p.getPeriodoId(), p.getConcepto(), p.getMonto(),
                    p.getEstado().name(), p.getFechaVenc(), p.getPagadoAt(), p.getCreatedAt());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Pago p) {
        try {
            return jdbc.update("""
                      UPDATE pago SET concepto=?, monto=?, estado=?, fecha_venc=?, pagado_at=? WHERE id=?
                    """, p.getConcepto(), p.getMonto(), p.getEstado().name(), p.getFechaVenc(), p.getPagadoAt(),
                    p.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM pago WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
