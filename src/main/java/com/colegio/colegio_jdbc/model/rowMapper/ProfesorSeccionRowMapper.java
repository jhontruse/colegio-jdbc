package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.ProfesorSeccion;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class ProfesorSeccionRowMapper implements RowMapper<ProfesorSeccion> {
    @Override
    public ProfesorSeccion mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProfesorSeccion x = new ProfesorSeccion();
        x.setId(SqlUtil.str(rs, "id"));
        x.setSeccionId(SqlUtil.str(rs, "seccion_id"));
        x.setProfesorId(SqlUtil.str(rs, "profesor_id"));
        return x;
    }
}