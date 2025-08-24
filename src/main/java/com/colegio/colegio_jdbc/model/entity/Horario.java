package com.colegio.colegio_jdbc.model.entity;

import java.time.LocalTime;

import lombok.Data;

@Data
public class Horario {
    private String id; // UUID
    private String seccionId;
    private int diaSemana; // 1..7
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String aulaId;
}
