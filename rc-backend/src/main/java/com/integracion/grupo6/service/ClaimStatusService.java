package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.ClaimStatus;
import com.integracion.grupo6.dto.ClaimStatusDTO;

import java.util.List;

public interface ClaimStatusService {

    List<ClaimStatusDTO> findAll();

    ClaimStatus findCreatedStatus();

    ClaimStatus findCanceledStatus();

    ClaimStatus findClosingStatus();

}
