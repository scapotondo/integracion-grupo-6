package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public Claim findById(Long id) throws EntityNotFoundException {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if(optionalClaim.isPresent()) {
            return optionalClaim.get();
        } else {
            throw new EntityNotFoundException("No se encontro el Claim con Id " + id);
        }
    }
}
