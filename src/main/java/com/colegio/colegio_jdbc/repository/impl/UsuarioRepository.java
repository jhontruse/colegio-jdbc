package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;

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
       
    }
    @Override
    public Optional<Usuario> findByUsername(String username) {
       
    }
    @Override
    public List<Usuario> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    @Override
    public boolean existsByUsernameOrEmail(String username, String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByUsernameOrEmail'");
    }
    @Override
    public String create(Usuario u) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    @Override
    public int update(Usuario u) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public int delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
