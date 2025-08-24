package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Periodo {
    private String id; // UUID
    private String nombre; // unique (ej: 2025-I)
    private int anio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activo;
}
