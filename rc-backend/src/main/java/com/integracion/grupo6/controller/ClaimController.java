package com.integracion.grupo6.controller;

import java.security.Principal;
import java.util.List;

import com.integracion.grupo6.adapter.ClaimAdapter;
import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.domain.ClaimOrigin;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.dto.ClaimDTO;
import com.integracion.grupo6.dto.ClaimStatusDTO;
import com.integracion.grupo6.dto.ClaimTypeDTO;
import com.integracion.grupo6.exception.ClaimCreationException;
import com.integracion.grupo6.service.ClaimOriginService;
import com.integracion.grupo6.service.ClaimService;
import com.integracion.grupo6.service.ClaimStatusService;
import com.integracion.grupo6.service.ClaimTypeService;
import com.integracion.grupo6.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping({"/api/claim"})
public class ClaimController {

    @Autowired
    private ClaimService claimService;
    
    @Autowired
    private ClaimStatusService claimStatusService;

    @Autowired
    private ClaimTypeService claimTypeService;

    @Autowired
    private ClaimOriginService claimOriginService;

    @Autowired
    private ClaimAdapter claimAdapter;

    @Autowired
    private EmailService emailService;

    @GetMapping(path = {"/{id}"})
    public ClaimDTO getById(@PathVariable Long id) {
        return claimService.findById(id);
    }

    @GetMapping()
    public List<ClaimDTO> getAll() {
        return claimService.findAll();
    }

    @GetMapping(path = {"/cancel/{id}"})
    public ClaimDTO cancelClaim(@PathVariable Long id) {
        try {
            return claimService.cancel(id);
        } catch (ClaimCreationException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(path = {"/types"})
    public List<ClaimTypeDTO> findAllTypes() {
        return claimTypeService.findAll();
    }

    @GetMapping(path = {"/statuses"})
    public List<ClaimStatusDTO> findAllStatuses() {
        return claimStatusService.findAll();
    }

    @GetMapping(path = {"/origins"})
    public List<ClaimOrigin> getAllOrigins() {
        return claimOriginService.getAll();
    }

    @PostMapping
    public ClaimDTO create(@RequestBody ClaimDTO claimDTO, Principal principal){
        return createClaim(claimDTO, principal.getName());
    }

    @PostMapping(path = "/webclient")
    public ClaimDTO createWebClient(@RequestBody ClaimDTO claimDTO, Principal principal){
        claimDTO.setOrigin(ClaimOrigin.WEB);
        return createClaim(claimDTO, "webClient");
    }

    private ClaimDTO createClaim(ClaimDTO claimDTO, String creator) {
        try {
            Claim claim = claimService.create(claimDTO, creator);
            if (claim != null) {
                emailService.sendMessage(claim.getOrder().getClient().getEmail(), "Nuevo reclamo por compra", newClaimEmail(claim));
            }

            return claimAdapter.claimToDTO(claim);
        } catch (ClaimCreationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String newClaimEmail(Claim claim) {
        Order order = claim.getOrder();

        return String.format("Hola %s,\n" + 
            "Se ha creado un reclamo respecto de tu orden '%s'.\n" +
            "Detalles del reclamo:\n" +
            "- Numero de reclamo: %s\n" +
            "- Descripcion: %s\n\n" +
            "Te enviaremos un mail cuando el estado de tu reclamo cambie.\n Gracias!", order.getClient().getFullName(), order.getId(), claim.getId(), claim.getDescription());
    }

    @GetMapping(path = {"/order/{orderNumber}"})
    public ClaimDTO getClaimByOrder(@PathVariable String orderNumber){
        return claimService.getClaimByOrder(orderNumber);
    }
}