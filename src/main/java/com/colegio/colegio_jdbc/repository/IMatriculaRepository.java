package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Matricula;

public interface IMatriculaRepository {

    Optional<Matricula> findById(String id);

    List<Matricula> findBySeccion(String seccionId);

    List<Matricula> findByEstudiante(String estudianteId);

    String create(Matricula m);

    int update(Matricula m);

    int delete(String id);

}
