package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Entidad;
import com.integracion.grupo6.repository.EntidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "entidadService")
public class EntidadServiceImpl implements EntidadService {

    @Autowired
    private EntidadRepository entidadRepository;

    @Override
    public List<Entidad> findAll() {
        return entidadRepository.findAll();
    }
}
