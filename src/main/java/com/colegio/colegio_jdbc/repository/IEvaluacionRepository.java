package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Evaluacion;

public interface IEvaluacionRepository {

    Optional<Evaluacion> findById(String id);

    List<Evaluacion> findBySeccion(String seccionId);

    String create(Evaluacion e);

    int update(Evaluacion e);

    int delete(String id);

}
