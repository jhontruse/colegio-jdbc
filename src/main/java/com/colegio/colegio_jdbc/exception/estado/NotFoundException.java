package com.colegio.colegio_jdbc.exception.estado;

import com.colegio.colegio_jdbc.exception.AppException;

public class NotFoundException extends AppException {
    public NotFoundException(String message) {
        super("not_found", message);
    }
}
