package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Usuario;
import com.colegio.colegio_jdbc.model.rowMapper.UsuarioRowMapper;
import com.colegio.colegio_jdbc.repository.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsuarioRepository implements IUsuarioRepository {

    private final JdbcTemplate jdbc;
    private static final UsuarioRowMapper M = new UsuarioRowMapper();

    @Override
    public Optional<Usuario> findById(String id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject(
                    "SELECT id,username,email,password_hash,enabled,created_at,updated_at FROM usuario WHERE id=?", M,
                    id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        try {
            return Optional.ofNullable(jdbc.queryForObject(
                    "SELECT id,username,email,password_hash,enabled,created_at,updated_at FROM usuario WHERE username=?",
                    M, username));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Usuario> findAll() {
        try {
            return jdbc.query(
                    "SELECT id,username,email,password_hash,enabled,created_at,updated_at FROM usuario ORDER BY created_at DESC",
                    M);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public boolean existsByUsernameOrEmail(String username, String email) {
        try {
            Integer c = jdbc.queryForObject("SELECT COUNT(*) FROM usuario WHERE username=? OR email=?", Integer.class,
                    username, email);
            return c != null && c > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String create(Usuario u) {
        try {
            String id = Optional.ofNullable(u.getId()).filter(s -> !s.isBlank()).orElse(UUID.randomUUID().toString());
            jdbc.update("""
                      INSERT INTO usuario(id,username,email,password_hash,enabled) VALUES(?,?,?,?,?)
                    """, id, u.getUsername(), u.getEmail(), u.getPasswordHash(), u.isEnabled());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Usuario u) {
        try {
            return jdbc.update("UPDATE usuario SET username=?, email=?, enabled=? WHERE id=?",
                    u.getUsername(), u.getEmail(), u.isEnabled(), u.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM usuario WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
