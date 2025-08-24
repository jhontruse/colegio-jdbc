package com.colegio.colegio_jdbc.util;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SqlUtil {
    private SqlUtil() {
    }

    public static String str(ResultSet rs, String col) throws SQLException {
        String v = rs.getString(col);
        return (rs.wasNull() ? null : v);
    }

    public static Integer i(ResultSet rs, String col) throws SQLException {
        int v = rs.getInt(col);
        return (rs.wasNull() ? null : v);
        // para primitivos int usa rs.getInt directamente
    }

    public static boolean bool(ResultSet rs, String col) throws SQLException {
        boolean v = rs.getBoolean(col);
        // si necesitas Boolean, crea otro método que devuelva null si wasNull()
        return v;
    }

    public static BigDecimal dec(ResultSet rs, String col) throws SQLException {
        BigDecimal v = rs.getBigDecimal(col);
        return (rs.wasNull() ? null : v);
    }

    public static LocalDateTime ldt(ResultSet rs, String col) throws SQLException {
        Timestamp ts = rs.getTimestamp(col);
        return (ts == null ? null : ts.toLocalDateTime());
    }

    public static LocalDate ld(ResultSet rs, String col) throws SQLException {
        java.sql.Date d = rs.getDate(col);
        return (d == null ? null : d.toLocalDate());
    }

    public static LocalTime lt(ResultSet rs, String col) throws SQLException {
        java.sql.Time t = rs.getTime(col);
        return (t == null ? null : t.toLocalTime());
    }
}
