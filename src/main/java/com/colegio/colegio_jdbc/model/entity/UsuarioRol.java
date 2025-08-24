package com.colegio.colegio_jdbc.model.entity;

import lombok.Data;

@Data
public class UsuarioRol {
    private String usuarioId; // FK usuario.id
    private String rolId; // FK rol.id
}
