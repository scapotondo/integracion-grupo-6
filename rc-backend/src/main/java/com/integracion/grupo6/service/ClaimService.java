package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.dto.ClaimDTO;

import javax.persistence.EntityNotFoundException;

public interface ClaimService {

    ClaimDTO findById(Long id) throws EntityNotFoundException;

    Claim create(ClaimDTO claimDto);
}
