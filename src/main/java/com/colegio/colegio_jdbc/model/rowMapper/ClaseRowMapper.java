package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Clase;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class ClaseRowMapper implements RowMapper<Clase> {
    @Override
    public Clase mapRow(ResultSet rs, int rowNum) throws SQLException {
        Clase x = new Clase();
        x.setId(SqlUtil.str(rs, "id"));
        x.setSeccionId(SqlUtil.str(rs, "seccion_id"));
        x.setFecha(SqlUtil.ld(rs, "fecha"));
        x.setNroSesion(rs.getInt("nro_sesion"));
        x.setTema(SqlUtil.str(rs, "tema"));
        return x;
    }
}