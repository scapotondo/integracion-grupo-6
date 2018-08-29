package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.ClaimType;
import com.integracion.grupo6.repository.ClaimTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClaimTypeServiceImpl {

    @Autowired
    private ClaimTypeRepository claimTypeRepository;

    public List<ClaimType> findAll() {
        return claimTypeRepository.findAll();
    }

}
