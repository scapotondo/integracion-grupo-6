package com.integracion.grupo6.batch.adapter;

import com.integracion.grupo6.domain.Client;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.domain.Product;
import com.integracion.grupo6.dto.OrderIntegrationDTO;
import com.integracion.grupo6.exception.AlreadyIntegratedException;
import com.integracion.grupo6.service.ClientService;
import com.integracion.grupo6.service.OrderService;
import com.integracion.grupo6.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class OrderIntegrationAdapter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    public Order adapt(OrderIntegrationDTO dto) throws AlreadyIntegratedException {
        if (!orderService.exists(dto.getId())) {
            Order order = new Order();

            order.setId(dto.getId());
            order.setClient(validateClient(dto));
            order.setProduct(validateProduct(dto));

            return order;
        } else {
            throw new AlreadyIntegratedException("La venta con Id: " + dto.getId() + " ya fue integrada.");
        }
    }

    private Client validateClient(OrderIntegrationDTO dto) {
        try {
            return clientService.findByIdentification(dto.getClientIdentification());
        } catch (EntityNotFoundException ex) {
            Client client = new Client();

            client.setIdentification(dto.getClientIdentification());
            client.setFullName(dto.getClientFullName());
            client.setEmail(dto.getClientEmail());

            return clientService.save(client);
        }
    }

    private Product validateProduct(OrderIntegrationDTO dto) {
        try {
            return productService.findById(dto.getProductId());
        } catch (EntityNotFoundException ex) {
            Product product = new Product();

            product.setId(dto.getProductId());
            product.setDescription(dto.getProductDescription());

            return productService.save(product);
        }
    }


}
