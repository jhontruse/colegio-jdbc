package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Periodo;

public interface IPeriodoRepository {

    Optional<Periodo> findById(String id);

    List<Periodo> findAll();

    String create(Periodo p);

    int update(Periodo p);

    int delete(String id);

}
