package com.integracion.grupo6.adapter;

import com.integracion.grupo6.domain.ClaimType;
import com.integracion.grupo6.dto.ClaimTypeDTO;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
public class ClaimTypeAdapter implements EntityDtoAdapter<ClaimType, ClaimTypeDTO> {


    @Override
    public ClaimType toEntity(ClaimTypeDTO dto) {
        throw new NotImplementedException();
    }

    @Override
    public ClaimTypeDTO toDTO(ClaimType entity) {
        return new ClaimTypeDTO(entity.getId(), entity.getName(), entity.isLogistics());
    }
}
