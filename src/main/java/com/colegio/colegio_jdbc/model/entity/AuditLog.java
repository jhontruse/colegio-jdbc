package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AuditLog {
    private String id; // UUID
    private String usuarioId; // nullable
    private String accion;
    private String entidad;
    private String entidadId; // nullable
    private String payload; // JSON como String
    private LocalDateTime createdAt;
}
