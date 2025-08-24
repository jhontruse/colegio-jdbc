package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.Menu;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class MenuRowMapper implements RowMapper<Menu> {
    @Override
    public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
        Menu x = new Menu();
        x.setId(SqlUtil.str(rs, "id"));
        x.setParentId(SqlUtil.str(rs, "parent_id"));
        x.setNombre(SqlUtil.str(rs, "nombre"));
        x.setCodigo(SqlUtil.str(rs, "codigo"));
        x.setRuta(SqlUtil.str(rs, "ruta"));
        x.setIcono(SqlUtil.str(rs, "icono"));
        x.setOrden(rs.getInt("orden"));
        x.setVisible(SqlUtil.bool(rs, "visible"));
        x.setActivo(SqlUtil.bool(rs, "activo"));
        x.setCreatedAt(SqlUtil.ldt(rs, "created_at"));
        x.setUpdatedAt(SqlUtil.ldt(rs, "updated_at"));
        return x;
    }
}