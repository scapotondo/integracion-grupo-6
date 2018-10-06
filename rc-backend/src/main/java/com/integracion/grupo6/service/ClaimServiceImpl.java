package com.integracion.grupo6.service;

import com.integracion.grupo6.adapter.ClaimAdapter;
import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.domain.ClaimStatus;
import com.integracion.grupo6.domain.ClaimType;
import com.integracion.grupo6.domain.Client;
import com.integracion.grupo6.dto.ClaimDTO;
import com.integracion.grupo6.exception.ClaimCreationException;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.domain.User;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClaimAdapter claimAdapter;


    @Override
    public List<ClaimDTO> findAll() throws EntityNotFoundException {
        List<ClaimDTO> dtos = new ArrayList<>();

        for (Claim claim : claimRepository.findAll()) {
            dtos.add(claimAdapter.claimToDTO(claim));
        }

        return dtos;
    }

    @Override
    public ClaimDTO findById(Long id) throws EntityNotFoundException {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if(optionalClaim.isPresent()) {
            return claimAdapter.claimToDTO(optionalClaim.get());
        } else {
            throw new EntityNotFoundException("No se encontro el Claim con Id " + id);
        }
    }

    @Override
    public Claim create(ClaimDTO claimDto, String username) throws ClaimCreationException {
        ClaimStatus status = claimStatusRepository.getOne(0L);
        Optional<ClaimType> type = claimTypeRepository.findById(claimDto.getType().getId());
        Optional<Order> order = orderRepository.findById(claimDto.getOrderId());
        User user = userRepository.findByUsername(username);
        Claim claim = new Claim();

        if (!type.isPresent()) {
            throw new ClaimCreationException(String.format("No existe el tipo de reclamo con id %d", claimDto.getType().getId()));
        }

        if (!order.isPresent()) {
            throw new ClaimCreationException(String.format("No existe la orden con id %d", claimDto.getOrderId()));
        }

        if (!order.get().getClient().getIdentification().equals(claimDto.getClientIdentification())) {
            throw new ClaimCreationException(
                    String.format(
                            "El cliente %s no esta autorizado a crear reclamos para la orden %d",
                            claimDto.getClientIdentification(), claimDto.getOrderId()));
        }


        claim.setClaimOrigin(claimDto.getOrigin());
        claim.setClaimType(type.get());
        claim.setOrder(order.get());
        claim.setCreationDate(Date.from(Instant.now()));
        claim.setDescription(claimDto.getDescription());
        claim.setUser(user);

        if (status != null && status.getId() != null) {
            claim.setClaimStatus(status);
        }

        return claimRepository.save(claim);
    }
    
    public ClaimDTO getClaimByOrder(String orderNumber) {
        return claimAdapter.claimToDTO(claimRepository.findByOrderId(Long.valueOf(orderNumber)));
    }
}
