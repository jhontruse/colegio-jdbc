package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Curso;

public interface ICursoRepository {

    Optional<Curso> findById(String id);

    List<Curso> findAll();

    String create(Curso c);

    int update(Curso c);

    int delete(String id);

}
