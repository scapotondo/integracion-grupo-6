package com.integracion.grupo6.controller;

import java.security.Principal;
import java.util.List;

import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.domain.ClaimOrigin;
import com.integracion.grupo6.dto.ClaimDTO;
import com.integracion.grupo6.dto.ClaimStatusDTO;
import com.integracion.grupo6.dto.ClaimTypeDTO;
import com.integracion.grupo6.exception.ClaimCreationException;
import com.integracion.grupo6.service.ClaimOriginService;
import com.integracion.grupo6.service.ClaimService;
import com.integracion.grupo6.service.ClaimStatusService;
import com.integracion.grupo6.service.ClaimTypeService;

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
    public Claim create(@RequestBody ClaimDTO claim, Principal principal){
        try {
            return claimService.create(claim, principal.getName());
        } catch (ClaimCreationException e) {
            e.printStackTrace();
            return null;
        }
    }
}