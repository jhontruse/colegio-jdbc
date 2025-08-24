package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.UsuarioRol;
import com.colegio.colegio_jdbc.model.rowMapper.UsuarioRolRowMapper;
import com.colegio.colegio_jdbc.repository.IUsuarioRolRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsuarioRolRepository implements IUsuarioRolRepository {

    private final JdbcTemplate jdbc;
    private static final UsuarioRolRowMapper M = new UsuarioRolRowMapper();

    @Override
    public List<UsuarioRol> findAllByUsuario(String usuarioId) {
        try {
            return jdbc.query("SELECT usuario_id,rol_id FROM usuario_rol WHERE usuario_id=?", M, usuarioId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int add(String usuarioId, String rolId) {
        try {
            return jdbc.update("INSERT INTO usuario_rol(usuario_id,rol_id) VALUES(?,?)", usuarioId, rolId);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int remove(String usuarioId, String rolId) {
        try {
            return jdbc.update("DELETE FROM usuario_rol WHERE usuario_id=? AND rol_id=?", usuarioId, rolId);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int removeAllByUsuario(String usuarioId) {
        try {
            return jdbc.update("DELETE FROM usuario_rol WHERE usuario_id=?", usuarioId);
        } catch (Exception e) {
            return 0;
        }
    }

}
