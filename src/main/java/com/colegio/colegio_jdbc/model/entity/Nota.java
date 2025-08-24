package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Nota {
    private String id; // UUID
    private String evaluacionId;
    private String estudianteId; // persona.id
    private double valor; // 0..20 (o 0..100)
    private LocalDateTime fechaRegistro;
}
