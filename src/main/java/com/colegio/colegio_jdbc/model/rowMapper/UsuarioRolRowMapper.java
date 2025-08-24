package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.UsuarioRol;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class UsuarioRolRowMapper implements RowMapper<UsuarioRol> {
    @Override
    public UsuarioRol mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsuarioRol x = new UsuarioRol();
        x.setUsuarioId(SqlUtil.str(rs, "usuario_id"));
        x.setRolId(SqlUtil.str(rs, "rol_id"));
        return x;
    }
}