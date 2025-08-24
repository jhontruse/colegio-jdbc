package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Persona;

public interface IPersonaRepository {

    Optional<Persona> findById(String id);

    List<Persona> findAll();

    String create(Persona p);

    int update(Persona p);

    int delete(String id);

}
