package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Menu {
    private String id; // UUID
    private String parentId; // nullable
    private String nombre;
    private String codigo; // unique
    private String ruta;
    private String icono; // nullable
    private int orden;
    private boolean visible;
    private boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
