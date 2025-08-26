package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Asistencia;

public interface IAsistenciaRepository {

    Optional<Asistencia> findById(String id);

    List<Asistencia> findByClase(String claseId);

    String create(Asistencia a);

    int update(Asistencia a);

    int delete(String id);

}
