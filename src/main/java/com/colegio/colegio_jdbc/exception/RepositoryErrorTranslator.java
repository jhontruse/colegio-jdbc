package com.colegio.colegio_jdbc.exception;

import java.sql.SQLException;

import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.dao.DuplicateKeyException;

import com.colegio.colegio_jdbc.exception.estado.ConcurrencyException;
import com.colegio.colegio_jdbc.exception.estado.DatabaseException;
import com.colegio.colegio_jdbc.exception.estado.ForeignKeyConstraintException;
import com.colegio.colegio_jdbc.exception.estado.UniqueConstraintException;

public final class RepositoryErrorTranslator {

    private RepositoryErrorTranslator() {
    }

    public static RuntimeException translate(String action, Throwable ex) {
        if (ex instanceof DuplicateKeyException dk) {
            return new UniqueConstraintException("Violación de unicidad durante " + action, dk);
        }
        if (ex instanceof DataIntegrityViolationException dive) {
            Throwable cause = dive.getCause();
            if (cause instanceof SQLException sql) {
                String sqlState = sql.getSQLState(); // ej. 23000
                int errorCode = sql.getErrorCode(); // MySQL: 1451/1452 FK, 1062 unique
                if ("23000".equals(sqlState)) {
                    // MySQL códigos típicos:
                    if (errorCode == 1062)
                        return new UniqueConstraintException("Registro duplicado en " + action, dive);
                    if (errorCode == 1451 || errorCode == 1452)
                        return new ForeignKeyConstraintException("Restricción de clave foránea en " + action, dive);
                }
            }
            return new DatabaseException("Error de integridad en " + action, dive);
        }
        if (ex instanceof CannotAcquireLockException || ex instanceof DeadlockLoserDataAccessException) {
            return new ConcurrencyException("Conflicto de concurrencia en " + action, ex);
        }
        if (ex instanceof DataAccessException dae) {
            return new DatabaseException("Error de base de datos en " + action, dae);
        }
        return (ex instanceof AppException) ? (AppException) ex
                : new DatabaseException("Fallo inesperado en " + action, ex);
    }
}