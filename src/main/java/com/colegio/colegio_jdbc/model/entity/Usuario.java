package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Usuario {
    private String id; // UUID
    private String username; // unique
    private String email; // unique
    private String passwordHash;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
