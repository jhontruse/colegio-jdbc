package com.colegio.colegio_jdbc.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.colegio.colegio_jdbc.model.enums.EstadoPago;

import lombok.Data;

@Data
public class Pago {
    private String id; // UUID
    private String personaId;
    private String periodoId;
    private String concepto;
    private BigDecimal monto;
    private EstadoPago estado;
    private LocalDate fechaVenc;
    private LocalDateTime pagadoAt; // nullable
    private LocalDateTime createdAt;
}
