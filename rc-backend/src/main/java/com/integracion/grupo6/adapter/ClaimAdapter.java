package com.integracion.grupo6.adapter;

import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.dto.ClaimDTO;
import com.integracion.grupo6.dto.ClaimStatusDTO;
import com.integracion.grupo6.dto.ClaimTypeDTO;
import org.springframework.stereotype.Component;

@Component
public class ClaimAdapter {

    public ClaimDTO claimToDTO(Claim claim){
        ClaimDTO dto = new ClaimDTO();
        dto.setId(claim.getId());
        dto.setOrigin(claim.getClaimOrigin());
        dto.setStatus(new ClaimStatusDTO(claim.getClaimStatus().getId(), claim.getClaimStatus().getName()));
        dto.setOrderId(claim.getOrder().getId());
        dto.setDescription(claim.getDescription());
        dto.setType(new ClaimTypeDTO(claim.getClaimType().getId(), claim.getClaimType().getName(), claim.getClaimType().isLogistics()));
        return dto;
    }
}
