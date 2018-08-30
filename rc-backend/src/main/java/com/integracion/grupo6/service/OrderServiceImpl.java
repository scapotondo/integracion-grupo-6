package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findById(Long id) throws EntityNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            throw new EntityNotFoundException("No se encontro el Order con Identification " + id);
        }
    }

    @Override
    public boolean exists(Long id) {
        try {
            findById(id);
            return true;
        } catch (EntityNotFoundException ex) {
            return false;
        }
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
