package com.colegio.colegio_jdbc.exception.estado;

import com.colegio.colegio_jdbc.exception.AppException;

public class ConcurrencyException extends AppException {
    public ConcurrencyException(String message, Throwable cause) {
        super("concurrency_error", message, cause);
    }
}
