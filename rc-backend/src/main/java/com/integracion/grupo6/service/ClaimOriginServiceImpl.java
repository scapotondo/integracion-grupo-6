package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.ClaimOrigin;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service
public class ClaimOriginServiceImpl implements ClaimOriginService {

    @Override
    public List<ClaimOrigin> getAll() {
        return Arrays.asList(ClaimOrigin.values());
    }
}
