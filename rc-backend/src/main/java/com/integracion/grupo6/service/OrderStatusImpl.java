package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.OrderStatus;
import com.integracion.grupo6.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class OrderStatusImpl implements OrderStatusService {

    private static final long DEFAULT_STATUS = 1L;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public OrderStatus getDefaultOrderStatus() {
        return findById(DEFAULT_STATUS);
    }

    public OrderStatus findById(Long id) {
        Optional<OrderStatus> optionalClient = orderStatusRepository.findById(id);
        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new EntityNotFoundException("No se encontro el Client con Identification " + id);
        }
    }
}
