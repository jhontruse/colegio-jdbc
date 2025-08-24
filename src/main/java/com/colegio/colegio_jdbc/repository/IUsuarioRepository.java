package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Usuario;

public interface IUsuarioRepository {

    Optional<Usuario> findById(String id);

    Optional<Usuario> findByUsername(String username);

    List<Usuario> findAll();

    boolean existsByUsernameOrEmail(String username, String email);

    String create(Usuario u);

    int update(Usuario u);

    int delete(String id);

}
