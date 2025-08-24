package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Curso;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class CursoRowMapper implements RowMapper<Curso> {
    @Override
    public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
        Curso x = new Curso();
        x.setId(SqlUtil.str(rs, "id"));
        x.setCodigo(SqlUtil.str(rs, "codigo"));
        x.setNombre(SqlUtil.str(rs, "nombre"));
        x.setCreditos(SqlUtil.i(rs, "creditos"));
        x.setHorasSemanales(SqlUtil.i(rs, "horas_semanales"));
        x.setNivel(SqlUtil.str(rs, "nivel"));
        x.setCreatedAt(SqlUtil.ldt(rs, "created_at"));
        return x;
    }
}