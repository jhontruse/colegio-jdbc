package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Asistencia;
import com.colegio.colegio_jdbc.model.enums.EstadoAsistencia;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class AsistenciaRowMapper implements RowMapper<Asistencia> {
    @Override
    public Asistencia mapRow(ResultSet rs, int rowNum) throws SQLException {
        Asistencia x = new Asistencia();
        x.setId(SqlUtil.str(rs, "id"));
        x.setClaseId(SqlUtil.str(rs, "clase_id"));
        x.setEstudianteId(SqlUtil.str(rs, "estudiante_id"));
        String est = SqlUtil.str(rs, "estado");
        x.setEstado(est == null ? null : EstadoAsistencia.valueOf(est));
        x.setJustificacion(SqlUtil.str(rs, "justificacion"));
        x.setMarcadaAt(SqlUtil.ldt(rs, "marcada_at"));
        return x;
    }
}