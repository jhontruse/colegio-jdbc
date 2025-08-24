package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Persona;
import com.colegio.colegio_jdbc.model.enums.Sexo;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class PersonaRowMapper implements RowMapper<Persona> {
    @Override
    public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
        Persona x = new Persona();
        x.setId(SqlUtil.str(rs, "id"));
        x.setUsuarioId(SqlUtil.str(rs, "usuario_id"));
        x.setNombres(SqlUtil.str(rs, "nombres"));
        x.setApePaterno(SqlUtil.str(rs, "ape_paterno"));
        x.setApeMaterno(SqlUtil.str(rs, "ape_materno"));
        x.setDni(SqlUtil.str(rs, "dni"));
        String sexo = SqlUtil.str(rs, "sexo");
        x.setSexo(sexo == null ? null : Sexo.valueOf(sexo));
        x.setFechaNac(SqlUtil.ld(rs, "fecha_nac"));
        x.setTelefono(SqlUtil.str(rs, "telefono"));
        x.setDireccion(SqlUtil.str(rs, "direccion"));
        x.setEstado(SqlUtil.bool(rs, "estado"));
        x.setCreatedAt(SqlUtil.ldt(rs, "created_at"));
        x.setUpdatedAt(SqlUtil.ldt(rs, "updated_at"));
        return x;
    }
}