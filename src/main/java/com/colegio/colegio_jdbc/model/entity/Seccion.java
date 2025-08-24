package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDateTime;

import com.colegio.colegio_jdbc.model.enums.EstadoSeccion;

import lombok.Data;

@Data
public class Seccion {
    private String id; // UUID
    private String cursoId;
    private String periodoId;
    private String codigo; // único junto con cursoId+periodoId
    private int capacidad;
    private EstadoSeccion estado;
    private LocalDateTime createdAt;
}
