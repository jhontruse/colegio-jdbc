package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Horario;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class HorarioRowMapper implements RowMapper<Horario> {
    @Override
    public Horario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Horario x = new Horario();
        x.setId(SqlUtil.str(rs, "id"));
        x.setSeccionId(SqlUtil.str(rs, "seccion_id"));
        x.setDiaSemana(rs.getInt("dia_semana"));
        x.setHoraInicio(SqlUtil.lt(rs, "hora_inicio"));
        x.setHoraFin(SqlUtil.lt(rs, "hora_fin"));
        x.setAulaId(SqlUtil.str(rs, "aula_id"));
        return x;
    }
}