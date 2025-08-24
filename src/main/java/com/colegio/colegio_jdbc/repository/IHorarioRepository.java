package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Horario;

public interface IHorarioRepository {

    Optional<Horario> findById(String id);

    List<Horario> findBySeccion(String seccionId);

    String create(Horario h);

    int update(Horario h);

    int delete(String id);

}
