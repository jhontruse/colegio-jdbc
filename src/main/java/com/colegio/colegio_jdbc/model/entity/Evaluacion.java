package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Evaluacion {
    private String id; // UUID
    private String seccionId;
    private String nombre; // único en la sección
    private double ponderacion; // 0..100
    private LocalDate fecha; // nullable
}
