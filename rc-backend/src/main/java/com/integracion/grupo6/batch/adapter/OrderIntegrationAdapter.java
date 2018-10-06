package com.integracion.grupo6.batch.adapter;

import com.integracion.grupo6.domain.Client;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.domain.Product;
import com.integracion.grupo6.dto.OrderIntegrationDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderIntegrationAdapter {

    public Order adapt(OrderIntegrationDTO dto) {
        Order order = new Order();

        order.setId(dto.getId());
        order.setClient(adaptClient(dto));
        order.setProduct(adaptProduct(dto));

        return order;
    }

    public Client adaptClient(OrderIntegrationDTO dto) {
        Client client = new Client();

        client.setIdentification(dto.getClientIdentification());
        client.setFullName(dto.getClientFullName());
        client.setEmail(dto.getClientEmail());

        return client;
    }

    public Product adaptProduct(OrderIntegrationDTO dto) {
        Product product = new Product();

        product.setId(dto.getProductId());
        product.setDescription(dto.getProductDescription());

        return product;
    }


}
