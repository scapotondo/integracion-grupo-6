package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.domain.ClaimStatus;
import com.integracion.grupo6.domain.ClaimType;
import com.integracion.grupo6.domain.Client;
import com.integracion.grupo6.dto.ClaimDTO;
import com.integracion.grupo6.repository.ClaimRepository;
import com.integracion.grupo6.repository.ClaimStatusRepository;
import com.integracion.grupo6.repository.ClaimTypeRepository;
import com.integracion.grupo6.repository.ClientRepository;
import com.integracion.grupo6.repository.OrderRepository;
import com.integracion.grupo6.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import com.integracion.grupo6.domain.Order;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ClaimTypeRepository claimTypeRepository;

    @Autowired
    private ClaimStatusRepository claimStatusRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Claim findById(Long id) throws EntityNotFoundException {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if(optionalClaim.isPresent()) {
            return optionalClaim.get();
        } else {
            throw new EntityNotFoundException("No se encontro el Claim con Id " + id);
        }
    }

    @Override
    public Claim create(ClaimDTO claimDto) {
        ClaimType type = claimTypeRepository.findById(claimDto.getType().getId()).get();
        ClaimStatus status = claimStatusRepository.getOne(0L);
        Order order = orderRepository.findById(claimDto.getOrderId()).get();
        Claim claim = new Claim();

        if (order.getClient().getIdentification().equals(claimDto.getClientIdentification())) {

            claim.setClaimOrigin(claimDto.getOrigin());
            claim.setClaimType(type);
            claim.setCreationDate(Date.from(Instant.now()));
            claim.setDescription(claimDto.getDescription());
            claim.setOrder(order);
            // claim.setUser(user); // TODO: add user

            if (status != null && status.getId() != null) {
                claim.setClaimStatus(status);
            }

            claimRepository.save(claim);
        }

        return claim;
	}
}
