package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Nota;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class NotaRowMapper implements RowMapper<Nota> {
    @Override
    public Nota mapRow(ResultSet rs, int rowNum) throws SQLException {
        Nota x = new Nota();
        x.setId(SqlUtil.str(rs, "id"));
        x.setEvaluacionId(SqlUtil.str(rs, "evaluacion_id"));
        x.setEstudianteId(SqlUtil.str(rs, "estudiante_id"));
        x.setValor(rs.getDouble("valor"));
        x.setFechaRegistro(SqlUtil.ldt(rs, "fecha_registro"));
        return x;
    }
}
