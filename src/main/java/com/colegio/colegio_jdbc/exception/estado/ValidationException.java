package com.colegio.colegio_jdbc.exception.estado;

import com.colegio.colegio_jdbc.exception.AppException;

public class ValidationException extends AppException {

    public ValidationException(String message) {
        super("validation_error", message);
    }

}
