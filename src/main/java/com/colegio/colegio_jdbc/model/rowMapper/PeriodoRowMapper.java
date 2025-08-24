package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Periodo;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class PeriodoRowMapper implements RowMapper<Periodo> {
    @Override
    public Periodo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Periodo x = new Periodo();
        x.setId(SqlUtil.str(rs, "id"));
        x.setNombre(SqlUtil.str(rs, "nombre"));
        x.setAnio(rs.getInt("anio"));
        x.setFechaInicio(SqlUtil.ld(rs, "fecha_inicio"));
        x.setFechaFin(SqlUtil.ld(rs, "fecha_fin"));
        x.setActivo(SqlUtil.bool(rs, "activo"));
        return x;
    }
}