package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Claim;

import javax.persistence.EntityNotFoundException;

public interface ClaimService {

    Claim findById(Long id) throws EntityNotFoundException;

}
