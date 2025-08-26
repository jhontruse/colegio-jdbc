package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.AuditLog;

public interface IAuditLogRepository {

    Optional<AuditLog> findById(String id);

    List<AuditLog> findAllByEntidad(String entidad, String entidadId);

    String create(AuditLog a);

    int delete(String id);

}
