package com.colegio.colegio_jdbc.exception;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    String code; // e.g. validation_error, not_found, unique_violation, etc.
    String message; // mensaje legible
    String path; // request path
    Integer status; // HTTP status code
    String correlationId; // X-Correlation-Id (MDC)
    OffsetDateTime timestamp; // ISO-8601
    Object details; // mapa/lista con errores campo a campo, etc.
}
