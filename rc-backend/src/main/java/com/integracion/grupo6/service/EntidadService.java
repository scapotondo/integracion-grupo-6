package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Entidad;

import java.util.List;

public interface EntidadService {

    List<Entidad> findAll();

    Entidad save(Entidad entidad);

}
