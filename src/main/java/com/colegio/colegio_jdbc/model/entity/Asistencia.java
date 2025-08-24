package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDateTime;

import com.colegio.colegio_jdbc.model.enums.EstadoAsistencia;

import lombok.Data;

@Data
public class Asistencia {
    private String id; // UUID
    private String claseId;
    private String estudianteId; // persona.id
    private EstadoAsistencia estado;
    private String justificacion; // nullable
    private LocalDateTime marcadaAt;
}
