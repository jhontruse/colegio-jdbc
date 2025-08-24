package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Rol;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class RolRowMapper implements RowMapper<Rol> {
    @Override
    public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rol x = new Rol();
        x.setId(SqlUtil.str(rs, "id"));
        x.setNombre(SqlUtil.str(rs, "nombre"));
        x.setDescripcion(SqlUtil.str(rs, "descripcion"));
        return x;
    }
}