package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Rol;

public interface IRolRepository {

    Optional<Rol> findById(String id);

    List<Rol> findAll();

    String create(Rol r);

    int update(Rol r);

    int delete(String id);

}
