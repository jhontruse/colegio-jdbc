package com.colegio.colegio_jdbc.exception.estado;

import com.colegio.colegio_jdbc.exception.AppException;

public class ForeignKeyConstraintException extends AppException {
    public ForeignKeyConstraintException(String message, Throwable cause) {
        super("fk_violation", message, cause);
    }
}
