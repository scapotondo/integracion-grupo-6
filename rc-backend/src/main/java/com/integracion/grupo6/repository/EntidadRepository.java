package com.integracion.grupo6.repository;

import com.integracion.grupo6.domain.Entidad;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface EntidadRepository extends Repository<Entidad, Long> {

    List findAll();

}
