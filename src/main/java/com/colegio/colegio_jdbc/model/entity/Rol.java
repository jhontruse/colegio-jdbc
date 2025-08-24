package com.colegio.colegio_jdbc.model.entity;

import lombok.Data;

@Data
public class Rol {
    private String id; // UUID
    private String nombre; // unique
    private String descripcion;
}
