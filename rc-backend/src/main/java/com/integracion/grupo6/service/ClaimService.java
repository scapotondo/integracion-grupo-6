package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.dto.ClaimDTO;
import com.integracion.grupo6.dto.ClaimResolutionDTO;
import com.integracion.grupo6.exception.ClaimCreationException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ClaimService {

    List<ClaimDTO> findAll() throws EntityNotFoundException;

    ClaimDTO findById(Long id) throws EntityNotFoundException;

    Claim create(ClaimDTO claimDto, String username) throws ClaimCreationException;

    ClaimDTO cancel(Long id) throws ClaimCreationException;

    ClaimDTO getClaimByOrder(String orderNumber);

    void resolveClaimEndpooint(ClaimResolutionDTO dto);
}
