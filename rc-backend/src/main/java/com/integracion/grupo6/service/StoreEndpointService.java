package com.integracion.grupo6.service;

import com.integracion.grupo6.dto.StoreIntegrationDTO;

public interface StoreEndpointService {

    void sendClaimToStore(StoreIntegrationDTO dto);

}
