package com.integracion.grupo6.controller;

import com.integracion.grupo6.dto.LogisticsEndpointDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/prueba-endpoint"})
public class PruebaLogisticsController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @PostMapping
    public void createWebClient(@RequestBody LogisticsEndpointDTO dto) {
        logger.warn("RECIBI POR ENDPOINT " + dto.getOrderId());
    }

}
