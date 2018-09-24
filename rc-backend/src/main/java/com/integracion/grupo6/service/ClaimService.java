package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.dto.ClaimDTO;
import com.integracion.grupo6.exception.ClaimCreationException;

import javax.persistence.EntityNotFoundException;

public interface ClaimService {

    Claim findById(Long id) throws EntityNotFoundException;

    Claim create(ClaimDTO claimDto, String username) throws ClaimCreationException;
}
