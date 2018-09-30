package com.integracion.grupo6.batch.writer;

import com.integracion.grupo6.domain.Client;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.domain.OrderStatus;
import com.integracion.grupo6.domain.Product;
import com.integracion.grupo6.service.ClientService;
import com.integracion.grupo6.service.OrderService;
import com.integracion.grupo6.service.OrderStatusService;
import com.integracion.grupo6.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class OrderIntegrationWriter implements ItemWriter<Order> {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Override
    public void write(List<? extends Order> list) throws Exception {
        OrderStatus defaultStatus = orderStatusService.getDefaultOrderStatus();
        for (Order order : list) {
            try {
                order.setClient(saveClient(order.getClient()));
                order.setProduct(saveProduct(order.getProduct()));
                order.setOrderStatus(defaultStatus);
                orderService.save(order);
            } catch (Exception ex) {
                logger.error("Error al guardar la Order en repositorio", ex);
            }
        }
    }

    private Client saveClient(Client client) {
        try {
            return clientService.findByIdentification(client.getIdentification());
        } catch (EntityNotFoundException ex) {
            logger.info(String.format("Integrating client %s, %s", client.getIdentification(), client.getFullName()));
            return clientService.save(client);
        }
    }

    private Product saveProduct(Product product) {
        try {
            return productService.findById(product.getId());
        } catch (EntityNotFoundException ex) {
            logger.info(String.format("Integrating product %s, %s", product.getId(), product.getDescription()));
            return productService.save(product);
        }
    }
}
