package com.integracion.grupo6.batch.adapter;

import com.integracion.grupo6.domain.Client;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.dto.OrderIntegrationDTO;
import com.integracion.grupo6.exception.AlreadyIntegratedException;
import com.integracion.grupo6.service.ClientService;
import com.integracion.grupo6.service.OrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

public class SaleAdapter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    public Order adapt(OrderIntegrationDTO dto) throws AlreadyIntegratedException {
        if (!orderService.exists(dto.getId())) {
            Order order = new Order();

            order.setId(dto.getId());

        } else {
            throw new AlreadyIntegratedException("La venta con Id: " + dto.getId() + " ya fue integrada.");
        }
        return null;
    }

    private Client validateClient(OrderIntegrationDTO dto) {
        try {
            return clientService.getByIdentification(dto.getClientIdentification());
        } catch (EntityNotFoundException ex) {
            Client client = new Client();

            client.setIdentification(dto.getClientIdentification());
            client.setFullName(dto.getClientFullName());
            client.setEmail(dto.getClientEmail());

            return clientService.save(client);
        }
    }


}
