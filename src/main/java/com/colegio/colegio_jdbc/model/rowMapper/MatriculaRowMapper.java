package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Matricula;
import com.colegio.colegio_jdbc.model.enums.EstadoMatricula;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class MatriculaRowMapper implements RowMapper<Matricula> {
    @Override
    public Matricula mapRow(ResultSet rs, int rowNum) throws SQLException {
        Matricula x = new Matricula();
        x.setId(SqlUtil.str(rs, "id"));
        x.setEstudianteId(SqlUtil.str(rs, "estudiante_id"));
        x.setSeccionId(SqlUtil.str(rs, "seccion_id"));
        x.setFecha(SqlUtil.ldt(rs, "fecha"));
        String est = SqlUtil.str(rs, "estado");
        x.setEstado(est == null ? null : EstadoMatricula.valueOf(est));
        return x;
    }
}