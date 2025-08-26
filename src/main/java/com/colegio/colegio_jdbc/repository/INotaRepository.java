package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Nota;

public interface INotaRepository {

    Optional<Nota> findById(String id);

    List<Nota> findByEvaluacion(String evaluacionId);

    String create(Nota n);

    int update(Nota n);

    int delete(String id);

}
