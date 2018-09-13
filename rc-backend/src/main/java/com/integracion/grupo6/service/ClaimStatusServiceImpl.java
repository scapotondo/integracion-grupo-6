package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.ClaimStatus;
import com.integracion.grupo6.dto.ClaimStatusDTO;
import com.integracion.grupo6.repository.ClaimStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimStatusServiceImpl implements ClaimStatusService {

    @Autowired
    private ClaimStatusRepository claimStatusRepository;

    @Override
    public List<ClaimStatusDTO> findAll() {
        List<ClaimStatusDTO> statuses = new ArrayList<ClaimStatusDTO>();
        for(ClaimStatus type : claimStatusRepository.findAll()) {
            statuses.add(new ClaimStatusDTO(type.getId(), type.getName()));
        }

        return statuses;
    }
}
