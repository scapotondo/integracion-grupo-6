package com.integracion.grupo6.batch.processor;

import com.integracion.grupo6.batch.adapter.OrderIntegrationAdapter;
import com.integracion.grupo6.batch.validator.OrderValidator;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.dto.OrderIntegrationDTO;
import com.integracion.grupo6.exception.AlreadyIntegratedException;
import com.integracion.grupo6.exception.InvalidOrderFormatException;
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

    @Autowired
    private OrderValidator orderValidator;

    @Override
    public Order process(OrderIntegrationDTO dto) {
        if (orderService.exists(dto.getId())) {
            logger.warn(String.format("Ya existe una order con id %s", dto.getId()));
            return null;
        } else if (validar(dto)) {
            return adapter.adapt(dto);
        }
        return null;
    }

    private boolean validar(OrderIntegrationDTO dto) {
        try {
            orderValidator.validate(dto);
        } catch (InvalidOrderFormatException e) {
            logger.warn(String.format("[Order %d] %s", dto.getId(), e.getMessage()));
            return false;
        }
        return true;
    }

}
