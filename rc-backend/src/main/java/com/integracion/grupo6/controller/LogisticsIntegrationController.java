package com.integracion.grupo6.controller;

import com.integracion.grupo6.dto.ClaimResolutionDTO;
import com.integracion.grupo6.service.ClaimService;
import com.integracion.grupo6.service.LogisticsEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/api/public/reclamos/finalizar"})
public class LogisticsIntegrationController {

    @Autowired
    private ClaimService claimService;

    private final static String errorMessage = "El pedido %d no existe.";

    @PostMapping
    public ResponseEntity findById(@RequestBody ClaimResolutionDTO dto) {
        try {
            claimService.resolveClaimEndpooint(dto);
            return ResponseEntity.ok(dto.toString());
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(String.format(errorMessage, dto.getId_pedido()));
        }

    }


}
