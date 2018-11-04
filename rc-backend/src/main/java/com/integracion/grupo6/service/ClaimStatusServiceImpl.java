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

    private static Long CREATED_STATUS_ID = 0L;
    private static Long CLOSING_STATUS_ID = 1L;
    private static Long CANCELED_STATUS_ID = 2L;

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

    @Override
    public ClaimStatus findCreatedStatus() {
        return claimStatusRepository.findById(CREATED_STATUS_ID).get();
    }

    @Override
    public ClaimStatus findCanceledStatus() {
        return claimStatusRepository.findById(CANCELED_STATUS_ID).get();
    }

    @Override
    public ClaimStatus findClosingStatus() {
        return claimStatusRepository.findById(CLOSING_STATUS_ID).get();
    }
}
