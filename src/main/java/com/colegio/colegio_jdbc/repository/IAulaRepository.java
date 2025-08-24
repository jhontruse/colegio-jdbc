package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Aula;

public interface IAulaRepository {

    Optional<Aula> findById(String id);

    List<Aula> findAll();

    String create(Aula a);

    int update(Aula a);

    int delete(String id);

}
