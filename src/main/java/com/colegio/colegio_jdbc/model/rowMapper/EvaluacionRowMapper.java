package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Evaluacion;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class EvaluacionRowMapper implements RowMapper<Evaluacion> {
    @Override
    public Evaluacion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Evaluacion x = new Evaluacion();
        x.setId(SqlUtil.str(rs, "id"));
        x.setSeccionId(SqlUtil.str(rs, "seccion_id"));
        x.setNombre(SqlUtil.str(rs, "nombre"));
        // DECIMAL(5,2) -> double
        x.setPonderacion(rs.getDouble("ponderacion"));
        x.setFecha(SqlUtil.ld(rs, "fecha"));
        return x;
    }
}