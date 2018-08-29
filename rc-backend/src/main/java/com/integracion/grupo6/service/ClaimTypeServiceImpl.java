package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.ClaimType;
import com.integracion.grupo6.repository.ClaimTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimTypeServiceImpl implements ClaimTypeService {

    @Autowired
    private ClaimTypeRepository claimTypeRepository;

    @Override
    public List<ClaimType> findAll() {
        return claimTypeRepository.findAll();
    }

}
