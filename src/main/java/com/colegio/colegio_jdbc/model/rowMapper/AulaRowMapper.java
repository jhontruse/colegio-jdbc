package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Aula;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class AulaRowMapper implements RowMapper<Aula> {
    @Override
    public Aula mapRow(ResultSet rs, int rowNum) throws SQLException {
        Aula x = new Aula();
        x.setId(SqlUtil.str(rs, "id"));
        x.setCodigo(SqlUtil.str(rs, "codigo"));
        x.setAforo(rs.getInt("aforo"));
        x.setUbicacion(SqlUtil.str(rs, "ubicacion"));
        return x;
    }
}