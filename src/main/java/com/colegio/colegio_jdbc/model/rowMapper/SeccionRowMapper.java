package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Seccion;
import com.colegio.colegio_jdbc.model.enums.EstadoSeccion;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class SeccionRowMapper implements RowMapper<Seccion> {
    @Override
    public Seccion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Seccion x = new Seccion();
        x.setId(SqlUtil.str(rs, "id"));
        x.setCursoId(SqlUtil.str(rs, "curso_id"));
        x.setPeriodoId(SqlUtil.str(rs, "periodo_id"));
        x.setCodigo(SqlUtil.str(rs, "codigo"));
        x.setCapacidad(rs.getInt("capacidad"));
        String est = SqlUtil.str(rs, "estado");
        x.setEstado(est == null ? null : EstadoSeccion.valueOf(est));
        x.setCreatedAt(SqlUtil.ldt(rs, "created_at"));
        return x;
    }
}