package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.AuditLog;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class AuditLogRowMapper implements RowMapper<AuditLog> {
    @Override
    public AuditLog mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuditLog x = new AuditLog();
        x.setId(SqlUtil.str(rs, "id"));
        x.setUsuarioId(SqlUtil.str(rs, "usuario_id"));
        x.setAccion(SqlUtil.str(rs, "accion"));
        x.setEntidad(SqlUtil.str(rs, "entidad"));
        x.setEntidadId(SqlUtil.str(rs, "entidad_id"));
        x.setPayload(SqlUtil.str(rs, "payload"));
        x.setCreatedAt(SqlUtil.ldt(rs, "created_at"));
        return x;
    }
}