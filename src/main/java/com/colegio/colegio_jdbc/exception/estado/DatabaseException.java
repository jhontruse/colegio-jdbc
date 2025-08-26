package com.colegio.colegio_jdbc.exception.estado;

import com.colegio.colegio_jdbc.exception.AppException;

public class DatabaseException extends AppException {
    public DatabaseException(String message, Throwable cause) {
        super("database_error", message, cause);
    }
}
