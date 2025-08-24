package com.colegio.colegio_jdbc.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.colegio.colegio_jdbc.model.entity.RolMenu;
import com.colegio.colegio_jdbc.util.SqlUtil;

public class RolMenuRowMapper implements RowMapper<RolMenu> {
    @Override
    public RolMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
        RolMenu x = new RolMenu();
        x.setRolId(SqlUtil.str(rs, "rol_id"));
        x.setMenuId(SqlUtil.str(rs, "menu_id"));
        return x;
    }
}