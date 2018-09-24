package com.integracion.grupo6.batch.processor;

import com.integracion.grupo6.batch.adapter.OrderIntegrationAdapter;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.dto.OrderIntegrationDTO;
import com.integracion.grupo6.exception.AlreadyIntegratedException;
import com.integracion.grupo6.service.OrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderIntegrationProcessor implements ItemProcessor<OrderIntegrationDTO, Order> {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderIntegrationAdapter adapter;

    @Override
    public Order process(OrderIntegrationDTO dto) {
        if (orderService.exists(dto.getId())) {
            logger.warn(String.format("Ya existe una order con id %s", dto.getId()));
            return null;
        } else {
            return adapter.adapt(dto);
        }
    }

}
