package com.colegio.colegio_jdbc.repository;

import java.util.List;

import com.colegio.colegio_jdbc.model.entity.UsuarioRol;

public interface IUsuarioRolRepository {

    List<UsuarioRol> findAllByUsuario(String usuarioId);

    int add(String usuarioId, String rolId);

    int remove(String usuarioId, String rolId);

    int removeAllByUsuario(String usuarioId);

}
