package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.dto.ClaimDTO;
import com.integracion.grupo6.exception.ClaimCreationException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ClaimService {

    List<ClaimDTO> findAll() throws EntityNotFoundException;

    ClaimDTO findById(Long id) throws EntityNotFoundException;

    ClaimDTO create(ClaimDTO claimDto, String username) throws ClaimCreationException;

    ClaimDTO cancel(Long id) throws ClaimCreationException;

    public ClaimDTO getClaimByOrder(String orderNumber);
}
