package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Curso {
    private String id; // UUID
    private String codigo; // unique
    private String nombre;
    private Integer creditos; // nullable
    private Integer horasSemanales; // nullable
    private String nivel; // nullable
    private LocalDateTime createdAt;
}
