package com.integracion.grupo6.batch.writer;

import com.integracion.grupo6.domain.Entidad;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.service.EntidadService;
import com.integracion.grupo6.service.OrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderIntegrationWriter implements ItemWriter<Order> {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private OrderService orderService;

    @Override
    public void write(List<? extends Order> list) throws Exception {
        for (Order order : list) {
            try {
                orderService.save(order);
            } catch (Exception ex) {
                logger.error("Error al guardar la Order en repositorio", ex);
            }
        }
    }
}
