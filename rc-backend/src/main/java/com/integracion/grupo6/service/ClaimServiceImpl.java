package com.integracion.grupo6.service;

import com.integracion.grupo6.adapter.ClaimAdapter;
import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.domain.ClaimStatus;
import com.integracion.grupo6.domain.ClaimType;
import com.integracion.grupo6.dto.ClaimDTO;
import com.integracion.grupo6.dto.ClaimResolutionDTO;
import com.integracion.grupo6.dto.LogisticsEndpointDTO;
import com.integracion.grupo6.dto.StoreIntegrationDTO;
import com.integracion.grupo6.exception.ClaimCreationException;
import com.integracion.grupo6.repository.ClaimRepository;
import com.integracion.grupo6.repository.ClaimTypeRepository;
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
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClaimAdapter claimAdapter;

    @Autowired
    private ClaimStatusService claimStatusService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LogisticsEndpointService logisticsEndpointService;

    @Autowired
    private StoreEndpointService storeEndpointService;


    private final String LOGISTICS_EMAIL = "maximiliano.628@gmail.com";
    private final String RESOLVE_SUBJECT = "El reclamo %d ha sido resuelto.";
    private final String RESOLVE_MAIL = "El reclamo %d ha sido resuelto. El paquete fue entregado el dia %s.";

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
        if (optionalClaim.isPresent()) {
            return claimAdapter.claimToDTO(optionalClaim.get());
        } else {
            throw new EntityNotFoundException("No se encontro el Claim con Id " + id);
        }
    }

    @Override
    public Claim create(ClaimDTO claimDto, String username) throws ClaimCreationException {
        ClaimStatus status = claimStatusService.findCreatedStatus();
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

        Claim newClaim = claimRepository.save(claim);
        StoreIntegrationDTO dto = new StoreIntegrationDTO();
        dto.setIdPedido(newClaim.getOrder().getId().toString());
        dto.setEstado(newClaim.getClaimStatus().getName());
        dto.setDescripcion(newClaim.getDescription());

        storeEndpointService.sendClaimToStore(dto);
        logisticsEndpointService.sendClaimToLogistics(newClaim.getOrder().getId().toString());
        if (newClaim.getClaimType().isLogistics()) {
        }
        return newClaim;
    }

    @Override
    public ClaimDTO cancel(Long id) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        Claim claim;
        if (optionalClaim.isPresent()) {
            claim = optionalClaim.get();
            claim.setClaimStatus(claimStatusService.findCanceledStatus());
            return claimAdapter.claimToDTO(claimRepository.save(claim));
        }
        return null;
    }

    public ClaimDTO getClaimByOrder(String orderNumber) {
        Claim claim = claimRepository.findByOrderId(Long.valueOf(orderNumber));
        if (claim != null) {
            return claimAdapter.claimToDTO(claim);
        }

        return null;
    }

    @Override
    public void resolveClaimEndpooint(ClaimResolutionDTO dto) {
        ClaimDTO claimByOrder = getClaimByOrder(dto.getId_pedido());
        if (claimByOrder == null) {
            throw new EntityNotFoundException("No se encontro el Claim con Id " + dto.getId_pedido());
        }
        Optional<Claim> optionalClaim = claimRepository.findById(claimByOrder.getId());
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            resolveClaim(claim);
            sendMailToClient(claim, dto.getFecha_entrega());
            StoreIntegrationDTO storeDto = new StoreIntegrationDTO();
            storeDto.setIdPedido(dto.getId_pedido());
            storeDto.setDescripcion(String.format("Se cerro el reclamo el dia %s", dto.getFecha_entrega()));
            storeDto.setEstado("CERRADO");
            storeEndpointService.sendClaimToStore(storeDto);
        } else {
            throw new EntityNotFoundException("No se encontro el Claim con Id " + dto.getId_pedido());
        }
    }

    private void sendMailToClient(Claim claim, String date) {
        emailService.sendMessage(claim.getOrder().getClient().getEmail(),
                String.format(RESOLVE_SUBJECT, claim.getId()),
                String.format(RESOLVE_MAIL, claim.getId(), date));
    }

    private void sendMailToLogistics(Claim claim, String date) {
        emailService.sendMessage(LOGISTICS_EMAIL,
                String.format(RESOLVE_SUBJECT, claim.getId()),
                String.format(RESOLVE_MAIL, claim.getId(), date));
    }

    private void resolveClaim(Claim claim) {
        claim.setClaimStatus(claimStatusService.findClosingStatus());
        claimRepository.save(claim);
    }

}
