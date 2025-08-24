package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Clase {
    private String id; // UUID
    private String seccionId;
    private LocalDate fecha;
    private int nroSesion;
    private String tema; // nullable
}
