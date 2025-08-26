package com.colegio.colegio_jdbc.exception;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.MDC;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.colegio.colegio_jdbc.exception.estado.ConcurrencyException;
import com.colegio.colegio_jdbc.exception.estado.DatabaseException;
import com.colegio.colegio_jdbc.exception.estado.ForeignKeyConstraintException;
import com.colegio.colegio_jdbc.exception.estado.NotFoundException;
import com.colegio.colegio_jdbc.exception.estado.UniqueConstraintException;
import com.colegio.colegio_jdbc.exception.estado.ValidationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final String CORR = "corrId";

    private ResponseEntity<ApiError> build(HttpStatus status, String code, String message, String path,
            Object details) {
        var err = ApiError.builder()
                .status(status.value())
                .code(code)
                .message(message)
                .path(path)
                .correlationId(MDC.get(CORR))
                .timestamp(OffsetDateTime.now())
                .details(details)
                .build();
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(err);
    }

    // ====== Excepciones propias (desde repos/servicios) ======
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, ex.getCode(), ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler({ ValidationException.class, UniqueConstraintException.class,
            ForeignKeyConstraintException.class })
    public ResponseEntity<ApiError> handleBadRequest(AppException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex.getCode(), ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler(ConcurrencyException.class)
    public ResponseEntity<ApiError> handleConflict(ConcurrencyException ex, HttpServletRequest req) {
        return build(HttpStatus.CONFLICT, ex.getCode(), ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ApiError> handleDatabase(DatabaseException ex, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getCode(), ex.getMessage(), req.getRequestURI(), null);
    }

    // ====== Validación / Binding / JSON ======
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpServletRequest req) {
        var details = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        fe -> fe.getField(),
                        Collectors.mapping(fe -> fe.getDefaultMessage(), Collectors.toList())));
        return build(HttpStatus.BAD_REQUEST, "validation_error", "Error de validación de campos", req.getRequestURI(),
                details);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiError> handleBind(BindException ex, HttpServletRequest req) {
        var details = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        fe -> fe.getField(),
                        Collectors.mapping(fe -> fe.getDefaultMessage(), Collectors.toList())));
        return build(HttpStatus.BAD_REQUEST, "validation_error", "Error de binding", req.getRequestURI(), details);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        Map<String, String> details = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        v -> pathOf(v), // property path → string
                        ConstraintViolation::getMessage,
                        (a, b) -> a // merge
                ));
        return build(HttpStatus.BAD_REQUEST, "validation_error", "Parámetros inválidos", req.getRequestURI(), details);
    }

    private static String pathOf(ConstraintViolation<?> v) {
        return v.getPropertyPath() == null ? "" : v.getPropertyPath().toString();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleMissingParam(MissingServletRequestParameterException ex,
            HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, "validation_error",
                "Falta parámetro requerido: " + ex.getParameterName(), req.getRequestURI(), null);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest req) {
        String msg = "Tipo inválido para parámetro '" + ex.getName() + "'";
        return build(HttpStatus.BAD_REQUEST, "validation_error", msg, req.getRequestURI(), null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleNotReadable(HttpMessageNotReadableException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, "validation_error", "JSON inválido o mal formado", req.getRequestURI(),
                null);
    }

    // ====== Fallbacks ======
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiError> handleDataAccess(DataAccessException ex, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "database_error", "Error de base de datos", req.getRequestURI(),
                null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "internal_error", "Error interno del servidor",
                req.getRequestURI(), null);
    }

}
