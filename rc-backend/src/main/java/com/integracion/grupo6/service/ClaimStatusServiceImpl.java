package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.ClaimStatus;
import com.integracion.grupo6.repository.ClaimStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimStatusServiceImpl implements ClaimStatusService {

    @Autowired
    private ClaimStatusRepository claimStatusRepository;

    @Override
    public List<ClaimStatus> findAll() {
        return claimStatusRepository.findAll();
    }
}
