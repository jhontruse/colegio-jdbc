package com.colegio.colegio_jdbc.exception.estado;

import com.colegio.colegio_jdbc.exception.AppException;

public class UniqueConstraintException extends AppException {
    public UniqueConstraintException(String message, Throwable cause) {
        super("unique_violation", message, cause);
    }
}