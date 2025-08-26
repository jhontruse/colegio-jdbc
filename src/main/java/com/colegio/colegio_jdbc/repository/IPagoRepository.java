package com.colegio.colegio_jdbc.repository;

import java.util.List;
import java.util.Optional;

import com.colegio.colegio_jdbc.model.entity.Pago;

public interface IPagoRepository {

    Optional<Pago> findById(String id);

    List<Pago> findByPersonaPeriodo(String personaId, String periodoId);

    String create(Pago p);

    int update(Pago p);

    int delete(String id);

}
