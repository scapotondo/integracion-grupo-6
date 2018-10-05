package com.integracion.grupo6.service;

import com.integracion.grupo6.adapter.ClaimTypeAdapter;
import com.integracion.grupo6.domain.ClaimType;
import com.integracion.grupo6.dto.ClaimTypeDTO;
import com.integracion.grupo6.repository.ClaimTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimTypeServiceImpl implements ClaimTypeService {

    @Autowired
    private ClaimTypeRepository claimTypeRepository;

    @Autowired
    private ClaimTypeAdapter claimTypeAdapter;

    @Override
    public List<ClaimTypeDTO> findAll() {
        List<ClaimTypeDTO> types = new ArrayList<ClaimTypeDTO>();
        for(ClaimType type : claimTypeRepository.findAll()) {
            types.add(claimTypeAdapter.toDTO(type));
        }

        return types;
    }
}
