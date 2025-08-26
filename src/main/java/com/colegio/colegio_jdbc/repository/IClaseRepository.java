package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Clase;

public interface IClaseRepository {

    Optional<Clase> findById(String id);

    List<Clase> findBySeccion(String seccionId);

    String create(Clase c);

    int update(Clase c);

    int delete(String id);

}
