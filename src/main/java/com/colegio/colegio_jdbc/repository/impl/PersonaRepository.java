package com.colegio.colegio_jdbc.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio_jdbc.model.entity.Persona;
import com.colegio.colegio_jdbc.model.rowMapper.PersonaRowMapper;
import com.colegio.colegio_jdbc.repository.IPersonaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PersonaRepository implements IPersonaRepository {

    private final JdbcTemplate jdbc;
    private static final PersonaRowMapper M = new PersonaRowMapper();

    @Override
    public Optional<Persona> findById(String id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("SELECT * FROM persona WHERE id=?", M, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Persona> findAll() {
        try {
            return jdbc.query("SELECT * FROM persona ORDER BY ape_paterno, ape_materno, nombres", M);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public String create(Persona p) {
        try {
            String id = Optional.ofNullable(p.getId()).filter(s -> !s.isBlank()).orElse(UUID.randomUUID().toString());
            jdbc.update(
                    """
                              INSERT INTO persona(id,usuario_id,nombres,ape_paterno,ape_materno,dni,sexo,fecha_nac,telefono,direccion,estado)
                              VALUES(?,?,?,?,?,?,?,?,?,?,?)
                            """,
                    id, p.getUsuarioId(), p.getNombres(), p.getApePaterno(), p.getApeMaterno(), p.getDni(),
                    p.getSexo().name(), p.getFechaNac(), p.getTelefono(), p.getDireccion(), p.isEstado());
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(Persona p) {
        try {
            return jdbc.update(
                    """
                              UPDATE persona SET usuario_id=?,nombres=?,ape_paterno=?,ape_materno=?,dni=?,sexo=?,fecha_nac=?,telefono=?,direccion=?,estado=?
                              WHERE id=?
                            """,
                    p.getUsuarioId(), p.getNombres(), p.getApePaterno(), p.getApeMaterno(), p.getDni(),
                    p.getSexo().name(), p.getFechaNac(), p.getTelefono(), p.getDireccion(), p.isEstado(), p.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            return jdbc.update("DELETE FROM persona WHERE id=?", id);
        } catch (Exception e) {
            return 0;
        }
    }

}
