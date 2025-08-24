package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Usuario;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class UsuarioRowMapper implements RowMapper<Usuario> {
    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Usuario x = new Usuario();
        x.setId(SqlUtil.str(rs, "id"));
        x.setUsername(SqlUtil.str(rs, "username"));
        x.setEmail(SqlUtil.str(rs, "email"));
        x.setPasswordHash(SqlUtil.str(rs, "password_hash"));
        x.setEnabled(SqlUtil.bool(rs, "enabled"));
        x.setCreatedAt(SqlUtil.ldt(rs, "created_at"));
        x.setUpdatedAt(SqlUtil.ldt(rs, "updated_at"));
        return x;
    }
}