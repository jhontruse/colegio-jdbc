package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.colegio.colegio_jdbc.model.enums.Sexo;

import lombok.Data;

@Data
public class Persona {
    private String id; // UUID
    private String usuarioId; // nullable
    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private String dni; // unique
    private Sexo sexo;
    private LocalDate fechaNac;
    private String telefono;
    private String direccion;
    private boolean estado; // 1 activo / 0 inactivo
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
