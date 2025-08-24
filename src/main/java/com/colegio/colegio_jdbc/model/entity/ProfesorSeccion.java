package com.colegio.colegio_jdbc.model.entity;

import lombok.Data;

@Data
public class ProfesorSeccion {
    private String id; // UUID
    private String seccionId;
    private String profesorId; // persona.id
}
