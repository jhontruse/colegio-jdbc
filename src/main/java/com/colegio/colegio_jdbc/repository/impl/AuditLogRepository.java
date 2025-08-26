package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.AuditLog;
import com.colegio.colegio_jdbc.model.rowMapper.AuditLogRowMapper;
import com.colegio.colegio_jdbc.repository.IAuditLogRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AuditLogRepository implements IAuditLogRepository {

    private final JdbcTemplate jdbc;
    private static final AuditLogRowMapper M = new AuditLogRowMapper();

    @Override
    public Optional<AuditLog> findById(String id) {
        try {
            var list = jdbc.query("SELECT * FROM audit_log WHERE id=?", M, id);
            return list.stream().findFirst();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<AuditLog> findAllByEntidad(String entidad, String entidadId) {
        try {
            return jdbc.query("SELECT * FROM audit_log WHERE entidad=? AND entidad_id=? ORDER BY created_at DESC",
                    M, entidad, entidadId);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(AuditLog a) {
        try {
            String id = Optional.ofNullable(a.getId()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO audit_log(id,usuario_id,accion,entidad,entidad_id,payload,created_at)
                      VALUES(?,?,?,?,?,?,?)
                    """, id, a.getUsuarioId(), a.getAccion(), a.getEntidad(), a.getEntidadId(), a.getPayload(),
                    a.getCreatedAt());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM audit_log WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
