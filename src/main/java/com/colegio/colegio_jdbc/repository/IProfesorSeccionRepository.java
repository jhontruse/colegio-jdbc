package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.ProfesorSeccion;

public interface IProfesorSeccionRepository {

    Optional<ProfesorSeccion> findById(String id);

    List<ProfesorSeccion> findAllBySeccion(String seccionId);

    String create(ProfesorSeccion ps);

    int delete(String id);

    int deleteBySeccionAndProfesor(String seccionId, String profesorId);

}
