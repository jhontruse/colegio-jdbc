package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDateTime;

import com.colegio.colegio_jdbc.model.enums.EstadoMatricula;

import lombok.Data;

@Data
public class Matricula {
    private String id; // UUID
    private String estudianteId; // persona.id
    private String seccionId;
    private LocalDateTime fecha;
    private EstadoMatricula estado;
}
