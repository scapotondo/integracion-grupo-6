package com.integracion.grupo6.batch.adapter;

import com.integracion.grupo6.domain.Client;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.domain.Product;
import com.integracion.grupo6.dto.OrderIntegrationDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderIntegrationAdapterTest {

    @Autowired
    private OrderIntegrationAdapter orderIntegrationAdapter;

    @Test
    public void adaptOrder() {
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setId(515151L);
        dto.setClientEmail("email@domain.com");
        dto.setClientFullName("Hombre Araña");
        dto.setClientIdentification("35111222");
        dto.setProductId("123456");
        dto.setProductDescription("Empanada");

        Order order = orderIntegrationAdapter.adapt(dto);

        Assert.assertEquals(order.getId(), dto.getId());
    }

    @Test
    public void adaptClient() {
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setClientEmail("email@domain.com");
        dto.setClientFullName("Hombre Araña");
        dto.setClientIdentification("35111222");

        Client client = orderIntegrationAdapter.adaptClient(dto);

        Assert.assertEquals(client.getIdentification(), dto.getClientIdentification());
        Assert.assertEquals(client.getEmail(), dto.getClientEmail());
        Assert.assertEquals(client.getFullName(), dto.getClientFullName());
    }

    @Test
    public void adaptProduct() {
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setProductId("123456");
        dto.setProductDescription("Empanada");

        Product product = orderIntegrationAdapter.adaptProduct(dto);

        Assert.assertEquals(product.getId(), dto.getProductId());
        Assert.assertEquals(product.getDescription(), dto.getProductDescription());
    }
}