package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Seccion;

public interface ISeccionRepository {

    Optional<Seccion> findById(String id);

    List<Seccion> findAll();

    String create(Seccion s);

    int update(Seccion s);

    int delete(String id);

}
