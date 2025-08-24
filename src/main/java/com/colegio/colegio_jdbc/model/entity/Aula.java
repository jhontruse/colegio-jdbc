package com.colegio.colegio_jdbc.model.entity;

import lombok.Data;

@Data
public class Aula {
    private String id; // UUID
    private String codigo; // unique
    private int aforo;
    private String ubicacion; // nullable
}
