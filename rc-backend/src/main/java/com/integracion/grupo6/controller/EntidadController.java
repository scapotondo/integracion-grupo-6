package com.integracion.grupo6.controller;

import com.integracion.grupo6.service.EntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/entidad"})
public class EntidadController {

    @Autowired
    private EntidadService entidadService;

    @GetMapping(path = {"/all"})
    public List findAll() {
        return entidadService.findAll();
    }

}
