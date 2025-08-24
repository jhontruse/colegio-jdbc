package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Pago;
import com.colegio.colegio_jdbc.model.enums.EstadoPago;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class PagoRowMapper implements RowMapper<Pago> {
    @Override
    public Pago mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pago x = new Pago();
        x.setId(SqlUtil.str(rs, "id"));
        x.setPersonaId(SqlUtil.str(rs, "persona_id"));
        x.setPeriodoId(SqlUtil.str(rs, "periodo_id"));
        x.setConcepto(SqlUtil.str(rs, "concepto"));
        x.setMonto(SqlUtil.dec(rs, "monto"));
        String est = SqlUtil.str(rs, "estado");
        x.setEstado(est == null ? null : EstadoPago.valueOf(est));
        x.setFechaVenc(SqlUtil.ld(rs, "fecha_venc"));
        x.setPagadoAt(SqlUtil.ldt(rs, "pagado_at"));
        x.setCreatedAt(SqlUtil.ldt(rs, "created_at"));
        return x;
    }
}